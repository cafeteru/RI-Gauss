package uo.ri.ui.util;

import uo.ri.util.exception.BusinessException;

public interface Action {
	void execute() throws BusinessException;
}
