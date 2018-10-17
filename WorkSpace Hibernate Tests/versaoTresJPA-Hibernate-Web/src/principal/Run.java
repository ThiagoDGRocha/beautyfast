package principal;

import java.util.ArrayList;

import modeloDAO.AdministratorJpaDAO;
import modeloEntidade.Administrator;
import modeloEntidade.Adress;
import modeloEntidade.Contact;
import modeloEntidade.Schedrule;
import modeloEntidade.Specialty;
import modeloEntidade.User;
import utils.State;

public class Run {

	public static void main(String[] args) {
	User user = new User();
	Adress adress = new Adress();
	Contact contact = new Contact();
	Schedrule schedrule = new Schedrule();
	Specialty specialty = new Specialty();
	Administrator admin = new Administrator();
	ArrayList<Schedrule> listSchedrules = new ArrayList<Schedrule>();
	ArrayList<Specialty> listSpecialties = new ArrayList<Specialty>();
	AdministratorJpaDAO dao = new AdministratorJpaDAO();
	
	admin.setAdminName("Thiago Guimarães");
	admin.setAdminCPF("020.182.981-99");
	user.setUserName("penks@live.com");
	user.setUserPassword("123456");
	user.setUserType("Administrator");
	admin.setAdminUser(user);
	adress.setAdrCEP("74968-506");
	adress.setAdrCity("Dublin");
	adress.setAdrComplement("Ap 354");
	adress.setAdrState(State.GO.toString());
	adress.setAdrStreet("Capel");
	admin.setAdminAdress(adress);
	contact.setContEmail("penks@live.com");
	contact.setContNumber("+55(62)991524205");
	admin.setAdminContact(contact);
	user.setAdmin(admin);
	adress.setAdmin(admin);
	contact.setAdmin(admin);
	dao.persist(admin);
	
	
	
	}
}
