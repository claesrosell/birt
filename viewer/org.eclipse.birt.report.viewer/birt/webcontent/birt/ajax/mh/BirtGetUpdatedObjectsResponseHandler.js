/******************************************************************************
 *	Copyright (c) 2004 Actuate Corporation and others.
 *	All rights reserved. This program and the accompanying materials 
 *	are made available under the terms of the Eclipse Public License v2.0
 *	which accompanies this distribution, and is available at
 *		http://www.eclipse.org/legal/epl-2.0.html
 *	
 *	Contributors:
 *		Actuate Corporation - Initial implementation.
 *****************************************************************************/
 
/**
 *	birtGetUpdatedObjectsResponseHandler
 *	...
 */
BirtGetUpdatedObjectsResponseHandler = function( ){ };

BirtGetUpdatedObjectsResponseHandler.prototype = Object.extend( new BirtBaseResponseHandler( ),
{
	/**
	 *	Process update response message. Trigger necessary UI updates.
	 *	See response schema for details.
	 *
	 *	@message, update response DOM.
	 *	@return, void
	 */
	__process: function( message )
	{
		if ( !message ) return;

		let updateContentList = [];
		let updateContentData = [];
		for ( let updateIndex = 0; updateIndex < message.update.length; updateIndex++ )
		{
			let update = message.update[updateIndex];
			if ( update.updateContent != undefined && update.updateContent != null ) {
				updateContentList.push( update.updateContent );
			}
			if ( update.updateData != undefined && update.updateData != null ) {
				updateContentData.push( update.updateData );
			}
		}

		this.__processUpdateContent( updateContentList );
		this.__processUpdateData( updateContentData );
	},
	
	/**
	 *	Process update response message. Trigger necessary UI updates.
	 *	See response schema for details.
	 *
	 *	@message, update response DOM.
	 *	@return, void
	 */
	__processUpdateContent: function( updates )
	{
		if ( !updates ) return;
		
		for ( var i = 0; i < updates.length; i++ )
		{
			try
			{
				var targetData = updates[i].target
				var targetType = targetData.substring( 0, 5 );
				var handler = this.associations[targetType];
				
				if ( !handler ) 
				{
					var error = { name : "CustomError",
								  message : ( "__processUpdateContent no handler registered for type: " + targetType ) };
					throw error;
				}
								
				if ( !updates[i].content ) 
				{
					var error = { name : "CustomError",
								  message : ( " __processUpdateContent empty contents" ) };
					throw error;	
				}
				
				/**************************************************************
				 * Data is too large, seems firefox will wrap the content into
				 * several children nodes of text type. Need more verification.
				 **************************************************************/
				
				// firefox can use textContent to retrieve the complete node content,
				// check this property first to avoid string concatation which is very
				// slow for large node.  
				var contentData = updates[i].content;
				if ( contentData )
				{
					handler.__cb_disposeEventHandlers( targetData );
					handler.__cb_render( targetData, contentData );
					let inits = updates[i].initializationId;

					let bookmarks = updates[i].bookmark;
					if ( bookmarks != undefined && bookmarks != null )
					{
						handler.__cb_installEventHandlers( targetData, inits, bookmarks );
					}
					else
					{
						handler.__cb_installEventHandlers( targetData, inits );
					}
				}
			}
			catch( error )
			{
				debug( "ERROR in birtGetUpdatedObjectsResponseHandler" );
				
				for( var i in error )
				{
					debug( "ERROR " + i + " : " + error[i] );
				}
			}			
		}
	},

	/**
	 *	Process update response message. Trigger necessary UI updates.
	 *	See response schema for details.
	 *
	 *	@message, update response DOM.
	 *	@return, void
	 */
	__processUpdateData: function( updates )
	{
		if ( !updates ) return;
		
		for ( let i = 0; i < updates.length; i++ )
		{
			let targets = updates[i].target;
			if ( !targets || targets == null ) continue;
			
			let datas = updates[i].data;
			if ( !datas || datas == null ) continue;

			let handler = null;
			try
			{
				handler = eval( targets );
			}
			catch ( e )
			{
			}
			if ( !handler || !handler.__cb_bind ) continue;
			
			handler.__cb_bind( datas );
		}
	},
	
	/**
	 *	Helper function to handle "Init" type
	 *	@param init "Init" element
	 */
	initList: function( init )
	{
		var initData = init.firstChild.data;
		var targetType = init.firstChild.data.substring( 0, 5 );
		var handler = this.associations[targetType];	
		if ( !handler ) 
		{
			var error = {name:"CustomError", message: ("initList invalid Init")};
			throw error;					
		}
		handler.__cb_installEventHandlers( initData );
	}
} );

var birtGetUpdatedObjectsResponseHandler = new BirtGetUpdatedObjectsResponseHandler( );