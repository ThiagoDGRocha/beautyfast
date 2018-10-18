package com.beautyfast.modelTests;

import java.util.List;

import com.beautyfast.controller.ClientController;
import com.beautyfast.modelEntidade.Client;

public class Run {

	public static void main(String[] args) {
//		LoginController loginController = new LoginController();	
//		User user = new User();
//		Contact contact1 = new Contact();
//		Contact contact2 = new Contact();
//		Contact contact3 = new Contact();
//		ArrayList<Contact> listContact = new ArrayList<>();
//		user.setUserName("Penks");
//		user.setUserPassword("zeBolinhas");
//		
//		contact1.setContNumber("+55-62-9555555555");
//		contact1.setUser(user);
//		listContact.add(contact1);
//		
//		contact2.setContNumber("+55-62-9555555555");
//		contact2.setUser(user);
//		listContact.add(contact2);
//		
//		contact3.setContNumber("+55-62-9555555555");
//		contact3.setUser(user);
//		listContact.add(contact3);
//		
//		user.setContact(listContact);
//		//JOptionPane.showMessageDialog(null, userRestControle.insere(user));
//		JOptionPane.showMessageDialog(null, userRestControle.buscaTodos().toString());
//		//JOptionPane.showMessageDialog(null, userRestControle.busca(11));
	
		ClientController cliControl = new ClientController();
		List<Client> listCli = cliControl.listAll();
		System.out.println(listCli.get(2).getClientName());
		
		
	}
}
