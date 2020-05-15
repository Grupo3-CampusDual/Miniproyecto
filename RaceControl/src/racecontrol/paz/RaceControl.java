package racecontrol.paz;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RaceControl {
	final static String PATHRACEHISTORY = "RaceHistory.txt";
	static ArrayList <Carreras> listaCarreras = new ArrayList<Carreras>();
	public static void  añadirCoches() throws IOException{

		String brand="a",model="m";
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce nombre Garaje: ");
		brand = scan.nextLine();
		String archivo = "Escuderias\\"+brand.concat(".txt");
		File f = new File(archivo);	
		FileOutputStream fos = new FileOutputStream(f,true);	
		ObjectOutputStream obs = new ObjectOutputStream(fos);
		while(!model.equalsIgnoreCase("fin")) {
			System.out.println("Modelo:");
			model = scan.nextLine();
			if(!model.equals("fin")) {
				Coche c = new Coche(brand,model,100);
				obs.writeObject(c);
			}
		}
		obs.close();
		fos.close();
	}
	
	public static void  añadirCarreras() throws IOException, ClassNotFoundException{
		String name="a";
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce nombre Carrera: ");
		name = scan.nextLine();
		String archivo = "Carreras\\"+name.concat(".txt");
		File f = new File(archivo);	
		FileOutputStream fos = new FileOutputStream(f,true);	
		ObjectOutputStream obs = new ObjectOutputStream(fos);
				Carreras c = new Carreras(name);
				obs.writeObject(c);
		obs.close();
		fos.close();
		listaCarreras = cargarCarreras();
	}
	public static Garage chooseGarage() throws ClassNotFoundException, IOException{
		
				File fileEscuderias = new File("Escuderias");
				File f1[] = fileEscuderias.listFiles();
				Scanner e = new Scanner(System.in);
				int i = 1;
				for(File f2: f1) {
					System.out.println(i+". "+f2.getName().substring(0, f2.getName().length()-4));
					i++;
				}
				System.out.println(i+". "+"Inter-Escuderias");
				do {
				System.out.println("Elige tu escudería!");
				try{
				i=e.nextInt();	}catch(InputMismatchException ime) {i=Integer.MIN_VALUE; e.next();}
				
				}while(i<1 || i>f1.length+1);
				ArrayList<Coche> lista =new ArrayList<Coche>();
				Garage g = new Garage("",lista);
				if(i==f1.length+1)
					g.fillGarageRandom();
				else
					g.fillGarage(f1[i-1]);
				
				return g;
	}
	
	public static void carrera(ArrayList <Carreras> listaCarreras, String tipo) throws ClassNotFoundException, IOException {
		Scanner e = new Scanner(System.in);
		Garage garage = chooseGarage();
		ArrayList<Coche> podium =new ArrayList<Coche>();
		int i=1;
		for(Carreras c : listaCarreras) {
			System.out.println(i+". "+c.getNombre());
			i++;
		}
		do {
		System.out.println("Elige tu carrrera!");
		try{
		i = e.nextInt();	}catch(InputMismatchException ime) {i=Integer.MIN_VALUE; e.next();}
		
		}while(i<1 || i>listaCarreras.size());	
		if(tipo.equals("Eliminatorio")) {
		MotorEliminar me = new MotorEliminar(listaCarreras.get(i-1),garage.getCarList());
		podium = me.simulaCarrera();
		System.out.println("La carrera eliminatoria "+me.getCarrera().getNombre()+" ha terminado!");
		}
		else {
		MotorEstandar me = new MotorEstandar(listaCarreras.get(i-1),garage.getCarList());
		podium = me.simulaCarrera();
		System.out.println("La carrera estandard "+me.getCarrera().getNombre()+" ha terminado!");
		}		
		File history = new File(PATHRACEHISTORY);
		FileWriter fw = new FileWriter(history,true);
		LocalDateTime date = LocalDateTime.now();
		fw.write(date.toString()+"\n");
		String st;
		if(tipo.equals("Eliminatorio"))
			st="Eliminatoria";
		else
			st="Estandard";
		fw.write(listaCarreras.get(i-1).getNombre()+"-- "+st+"\n");
		i=1;
		for(Coche c: podium) {
			System.out.println(i+". "+c.imprimir());
			fw.write(i+". "+c.imprimir()+"\n");
			i++;
		}
		fw.close();
	}
	public static int menu() {
		int opt = 0;
		Scanner e = new Scanner(System.in);
		do {
		System.out.println("1.Carrera eliminatoria");
		System.out.println("2.Carrera estandard");
		System.out.println("3.Torneo eliminatorio");
		System.out.println("4.Torneo Estandard");
		System.out.println("5.Añadir Carreras");
		System.out.println("6.Añadir Escuderia");
		System.out.println("7.Historico de Carreras");
		System.out.println("8.Historico de Torneos");
		System.out.println("9.Fin");
		System.out.println("Opcion: ");
		try {
		opt = e.nextInt();}catch(InputMismatchException ime) {opt=Integer.MIN_VALUE; e.next();}
		}while(opt<1 || opt>9);
		return opt;
	}
	
	public static void torneo(String tipo) {
		
	}
	public static ArrayList <Carreras> cargarCarreras() throws ClassNotFoundException, IOException {
		listaCarreras.clear();
		File fc = new File("Carreras");
		File fc1[] = fc.listFiles();
		for(File f : fc1) {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream obs = new ObjectInputStream(fis);
			listaCarreras.add((Carreras)obs.readObject());
			fis.close();
			obs.close();
		}
		return listaCarreras;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
			
			listaCarreras = cargarCarreras();
			int opt =0;
			do {
			switch(opt=menu()) {
			case 1:carrera(listaCarreras,"Eliminatoria"); break;
			case 2: carrera(listaCarreras,"Estandar"); break;
			case 3: 
				Torneo te = new Torneo(listaCarreras,"Eliminatoria");
				te.organizaTorneo();
				te.comenzarTorneo(chooseGarage());
				break;
			case 4: 
				Torneo ts = new Torneo(listaCarreras,"Estandard");
				ts.organizaTorneo();
				ts.comenzarTorneo(chooseGarage());			
				break;
			case 5: añadirCarreras();break;
			case 6: añadirCoches(); break;
			case 7: File historial = new File(PATHRACEHISTORY);
					Desktop ds = Desktop.getDesktop();
					ds.open(historial);
					break;
			case 8:
					File historialC = new File("tourneyHistory.txt");
					Desktop dsc = Desktop.getDesktop();
					dsc.open(historialC);
					break;			
			default: System.out.println("Fin del programa"); break;
			}
		}while(opt!=9);
	}
}
