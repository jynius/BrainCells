/*
 * 
 */
package us.jyni.cell.brain.base;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author jynius
 * @since 2020-08-06
 */
public class BaseController {

	/**
	 * @param binder instance of WebDataBinder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		PropertyEditor propertyEditor = new TimeFilledLocalDateTimeEditor("00:00:00");
	    binder.registerCustomEditor(LocalDateTime.class, propertyEditor);
	}
}
