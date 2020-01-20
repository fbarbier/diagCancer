package fr.bordeaux.isped.sitis.diagCancer.Service;


import fr.bordeaux.isped.sitis.diagCancer.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class PatientIdentificationWithTag{

	private static PatientService patientService;

	static ArrayList<String> listepat= new ArrayList<String>();

	public static void main(String[] args) throws Exception{

		List<Patient> patients = patientService.findAll();

		patients.forEach(item->{
			//liste pour appliquer les m�thodes (peut �tre incompr�hensible) phonex soundex...
			listepat.add(item.getNom() + "/" + item.getPrenom() + "/" + item.getSexe() + "/" + item.getDateNaissance() + "/" + item.getTrait());
		});
		
		//initialisation variables
		int Dis = 0;
		double Jar = 0;
		double JarW = 0;

		
		//on parcourt la liste une premi�re fois 
		for(int i = 0; i < listepat.size(); i++) {
			//on fait quelque chose que si l'élément n'est dans aucune liste
			
				//compyeur pour les doublons
				int cpt =0;
				//on parcourt la liste une deuxi�me fois pour comparer chaque �l�ment entre eux
				for(int j = i+1; j < listepat.size(); j++) {
					//calculer de la distance de levenshtein entre deux éléments
					Jar = JaroWinklerScore.jaro(listepat.get(i), listepat.get(j));
					//calculer de la distance de jaro-winkler entre deux éléments
					Dis = LevenshteinDistance.levenshteinDistance(listepat.get(i),listepat.get(j));

					if(Dis<=3 || Jar>0.95) {					

							cpt++;
							String[][] parsedArrays = new String[listepat.size()][6];

							for (int k = 0; k < listepat.size(); k++)
							{
								parsedArrays[i] = listepat.get(i).split("/");
							}

							for (int l = 0; l < parsedArrays.length; l++)
							{

								// Update JPA Function



								//String sql2 = "UPDATE tab_patient SET doublon = ? WHERE ";
								//sql2 +="Nom LIKE \"" + parsedArrays[i][0] + "\" AND ";
								//sql2 +="Prenom LIKE \"" + parsedArrays[i][1] + "\" AND ";
								//sql2 +="Sexe = " + parsedArrays[i][2] + " AND ";
								//sql2 +="DateNaissance LIKE \"" + parsedArrays[i][3] + "\";";
						    }
					}

		}
	}
		


}



