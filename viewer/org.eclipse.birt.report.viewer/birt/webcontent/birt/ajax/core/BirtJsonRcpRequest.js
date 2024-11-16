/******************************************************************************
 *	Copyright (c) 2004 Actuate Corporation and others.
 *	All rights reserved. This program and the accompanying materials 
 *	are made available under the terms of the Eclipse Public License v2.0
 *	which accompanies this distribution, and is available at
 *		http://www.eclipse.org/legal/epl-2.0.html
 *	
 *	Contributors:
 *		Claes Rosell - Initial implementation.
 *****************************************************************************/

/**
 *	BirtJsonRcpRequest
 *	...
 */
class BirtJsonRcpRequest {

	// Private fields
	#url = null;
	#message = null;
	#payload = null;

	constructor() {
		this.reset();
	}

	/**
	 * Retrieve the request message.
	 *
	 * @return, request message in DOM
	 */
	getMessage(message) {
		return this.#message;
	}

	/**
	   * Retrieve the request message.
	   *
	 * @return, request message in DOM
	 */
	setURL(url) {
		this.#url = url;
	}

	/**
	 * Retrieve the request message.
	 *
	 * @return, request message in DOM
	 */
	getURL() {
		return this.#url;
	}

	getJsonDocument() {
		// JSON-RPC Request
		let jsonRpcRequest = { "jsonrpc": "2.0", "method": "GetUpdatedObjects", "id": 1 };
		jsonRpcRequest["params"] = [];
		let argList = [];
		argList.push( this.#payload);

		jsonRpcRequest["params"] = argList;
		return JSON.stringify(jsonRpcRequest);
	}

	/**
	* Recreates the message payload
	*/
	reset() {
		let payload = {};
		payload["operation"] = [];
		this.#payload = payload;
	}

	/**
	 * Adds Operation type to message.
	 *
	 * @param id id of UIComponent
	 * @param operator name of desired operation (from Constants.js)
	 * @param data xmlElement type 'Data' or null if not applicable
	 * @param (optional) argument[1] - argument[n] object  {name: "paramName1", value: "paramValue1"}
	 */
	addOperation(id, type, operator, data) {
		const optionalArgs = 4; //number of params
		let operation = {};

		operation["target"] = {};
		operation["target"]["id"] = id;
		operation["target"]["type"] = type;
		operation["operator"] = operator;
		operation["oprand"] = [];

		// Oprands
		if (arguments.length > optionalArgs) //there are optional parameters
		{
			var operand, name, value;
			for (var i = optionalArgs; i < arguments.length; i++) {
				if (arguments[i].length && arguments[i].length > 0) {
					for (var j = 0; j < arguments[i].length; j++) {
						if (!arguments[i][j].name) {
							continue;
						}

						let operand = {};
						operand["name"] = arguments[i][j].name;
						operand["value"] = arguments[i][j].value;
						operation["oprand"].push(operand);
					}
				}
				else {
					if (arguments[i].name) {
						let operand = {};
						operand["name"] = arguments[i].name;
						operand["value"] = arguments[i].value;
						operation["oprand"].push(operand);
					}
				}
			}
		}

		// Add to list of operations
		this.#payload["operation"].push(operation);

		debug("ADDED OPERATION : new message body is\n" + JSON.stringify());
	}
}

let birtJsonRpcRequest = new BirtJsonRcpRequest();