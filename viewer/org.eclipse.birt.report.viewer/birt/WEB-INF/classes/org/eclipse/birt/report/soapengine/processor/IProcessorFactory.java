/*************************************************************************************
 * Copyright (c) 2004 Actuate Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Actuate Corporation - Initial implementation.
 ************************************************************************************/

package org.eclipse.birt.report.soapengine.processor;

import org.eclipse.birt.report.soapengine.api.ReportIdType;
import org.eclipse.birt.report.tinyjsonrpc.LegacyRpcFault;

public interface IProcessorFactory {
	void init() throws LegacyRpcFault;

	IComponentProcessor createProcessor(String category, ReportIdType component);
}
