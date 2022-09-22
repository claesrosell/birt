package org.eclipse.birt.report.designer.ui.preview.parameter;

import java.util.List;

import org.eclipse.birt.report.engine.api.IEngineTask;
import org.eclipse.birt.report.model.api.ScalarParameterHandle;

/**
 * @since 3.3
 *
 */
public class DateParam extends ScalarParam {

	/**
	 * @param handle
	 * @param engineTask
	 */
	public DateParam(ScalarParameterHandle handle, IEngineTask engineTask) {
		super(handle, engineTask);
	}

	@Override
	public List getValueList() {
		return List.of(getDefaultValue());
	}
}