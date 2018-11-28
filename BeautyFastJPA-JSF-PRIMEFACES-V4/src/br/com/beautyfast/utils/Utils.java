package br.com.beautyfast.utils;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
	public static <T> Object getObjectSession(String attribute) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession(true);
		return session.getAttribute(attribute);
	}

	public static Object getSessionAttribute(String attributeName) {
		try {
			ExternalContext ec = getExternalContext();
			if (ec != null) {
				Map attrMap = ec.getSessionMap();
				if (attrMap != null) {
					return attrMap.get(attributeName);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setSessionAttribute(String attributeName, Object attributeValue) {
		try {
			ExternalContext ec = getExternalContext();
			if (ec != null) {
				Map attrMap = ec.getSessionMap();
				if (attrMap != null) {
					attrMap.put(attributeName, attributeValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ExternalContext getExternalContext() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			if (facesContext == null) {
				return null;
			} else {
				return facesContext.getExternalContext();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
