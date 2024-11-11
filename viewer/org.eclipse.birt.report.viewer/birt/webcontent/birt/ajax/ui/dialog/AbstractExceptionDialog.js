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
 *	Birt error dialog.
 */
AbstractExceptionDialog = function( ) { };

AbstractExceptionDialog.prototype = Object.extend( new AbstractBaseDialog( ),
{
	__faultCode : "",

	__faultData : "",
	
	__setFaultContainersWidth: function( width )
	{		
		document.getElementById("faultStringContainer").style.width = width;
		document.getElementById("exceptionTraceContainer").style.width = width;
	},

	
	/**
	 * Formats the given stack trace for HTML output.
	 * @param data stack trace
	 * @return formatted HTML data
	 */
	__formatStackTrace : function( data )	
	{
		var faultData = "";
		if ( data )
		{
			faultData = data;
		}
		this.__faultData = faultData;
		return faultData.replace(/\r?\n/g,"<br/>").replace(/[\s]{1}at/g,"&nbsp;&nbsp;&nbsp;at");		
	},
	
	/**
	 *	Binding data to the dialog UI. Data includes zoom scaling factor.
	 *
	 *	@data, data DOM tree (schema TBD)
	 *	@return, void
	 */
	__bind: function( data )
	{
	 	if( !data )
	 	{
	 		return;
	 	}
		
	 	var oSpans = this.__instance.getElementsByTagName( 'SPAN' );
		
	 	// Prepare fault string (reason)
	 	var faultString = data["faultstring"];
	 	if ( faultString )
	 	{
			oSpans[0].innerHTML = faultString;
		}
		else
		{
			oSpans[0].innerHTML = "";
		}

	 	// Prepare fault detail (Stack traces)
	 	var faultDetail = data["detail"];
	 	if ( faultDetail && faultDetail.length > 0 )
	 	{
	 		var detailSpan = oSpans[1];
	 		for ( var detailIndex = 0; detailIndex < faultDetail.length; detailIndex++ )
	 		{

				var detailNode = faultDetail[detailIndex];
				if ( detailIndex > 0 )
				{
					detailSpan.appendChild( document.createElement("hr") );
				}
				var detailElement = document.createElement("div");	 				
				detailElement.style.whiteSpace = "nowrap";
				if ( detailIndex > 0 )
				{
					detailElement.style.borderTopStyle = "solid";
					detailElement.style.borderTopWidth = "1px";
				}
				

					var stackTrace = detailNode;
					stackTrace = this.__formatStackTrace( stackTrace )
					var stackTraceElement = document.createElement("span");
					stackTraceElement.innerHTML = stackTrace;
					detailElement.appendChild( stackTraceElement );		 				
					detailSpan.appendChild(detailElement);


	 		}
		}
		else
		{
			oSpans[1].innerHTML = "";
		}

		var faultCodeElement = data["faultcode"];
	 	if ( data["faultcode"] && data["faultcode"] != null )
	 	{
			this.__faultCode = data["faultcode"];
		}
		else
		{
			this.__faultCode = "";
		}
	
		if ( this.__faultCode == "DocumentProcessor.getPageNumber( )" )
		{
			birtEventDispatcher.broadcastEvent( 
				birtEvent.__E_GETPAGE_INIT, 
				{ name : Constants.PARAM_PAGE, value : 1 } 
				);
		}				
	
	}
} );