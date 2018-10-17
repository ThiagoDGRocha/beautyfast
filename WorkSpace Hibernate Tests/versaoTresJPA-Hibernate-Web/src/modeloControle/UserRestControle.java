package modeloControle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import modeloDAO.ClientJpaDAO;
import modeloEntidade.User;

@Path("user")
public class UserRestControle {
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/login")
//	public String login(User user) {
//		ClientJpaDAO userJpaDAO = new ClientJpaDAO();
//		try {
//			String psw = user.getUserPassword();
//			user = userJpaDAO.userLogin(user);
//			if (user.getUserPassword().equals(psw)) {
//				String str = "Usuário: " + user.getUserName() + "\nÚltimo acesso:" + user.getLastAccess()
//						+ "\nUsuário ativo: " + user.isUserActive();
//				return str;
//			} else {
//				return "Senha incorreta!";
//			}
//		} catch (Exception e) {
//			throw new WebApplicationException("ErrorUserLogin\n" + e + 5003);
//		}
//	}

	/**
	 * Method used to test connection.
	 * 
	 * @return String with response of the operation.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/test")
	public String teste() {
		return "User Controle na escuta!!";
	}
}
