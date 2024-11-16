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
 *	BirtJsonRpcResponse
 *	...
 */
BirtJsonRpcResponse = Class.create( );

BirtJsonRpcResponse.prototype =
{
	/**
	 *	Initialization routine required by "ProtoType" lib.
	 *
	 *	@return, void
	 */
	initialize: function( )
	{
	},
	
	/**
	 *	Process the JSON-RPC response, dispatcher message content to designated
	 *	message handlers.
	 *
	 *	@message, incoming JSON-RPC message payload
	 *	@return, void
	 */
	process : function( message ) {
		if ( message )
		{
			let resultObject = message["result"];
			let errorObject = message["error"];

			if ( resultObject ) {
				let responseType = resultObject["name"];
				if ( responseType )
				{
					let responsePayload = resultObject["data"];
					let handler = eval( 'birt' + responseType + 'Handler' );
					if ( handler )
					{
						handler.__process( responsePayload );
					}
				}
			} else if (errorObject) {
				// Error
				let legacyError = errorObject;
				birtExceptionDialog.__cb_bind( legacyError );
			}
		}
	}
}

var birtJsonRpcResponse = new BirtJsonRpcResponse();