import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.xmp.XMPConst;
import com.itextpdf.kernel.xmp.XMPMeta;
import com.itextpdf.kernel.xmp.XMPMetaFactory;
import com.itextpdf.kernel.xmp.XMPUtils;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;



public class JATSProcessor extends JFrame implements ActionListener {
	
	static String strIndexSec = "";
	static String strIndexFig = "";
	static String strIndexTable = "";
	static String strIndexEquation = "";
	static String strIndexRef = "";
	
	static String volume = "";
	static String issue = "";
	static String fPage = "";
	static String lPage = "";
	static String pubDate = "";
	
	static String journalTitle = "";
	static String issn = "";
	static String issn2 = "";
	static String v_issn = "";
	static String v_issn2 = "";
	static String publisherName = "";
	
	static String figCount = "";
	static String tableCount = "";
	static String equationCount = "";
	static String refCount = "";
	static String pageCount = "";
	
	static String otherQuery = "";
	
	private static final long serialVersionUID = 1L; 
    private JButton boton;         
    private JButton botonSelect;
    private JButton botonActualizaHead;
    private JButton botonSubefic;
    private JButton botonChequea;
    private JButton botonGenDOI;
    private JButton botonGenDOAJ;
    private JButton botonSubirOtros;
    private JButton botonSubirOtros1;
    private JTextArea area;
    private String fichero;
    private String nomBfichero="";
    private String nombre_original="";
    private ArrayList <String[]> ficherosSubir;
    private ArrayList <String[]> dois_referencias;
    private JCheckBox checkCorrespondencia;
    private JCheckBox checkTitulo;
    private JCheckBox checkTituloEn;
    private JCheckBox checkAutores;
    private JCheckBox checkAfiliacion;
    private JCheckBox checkResumen;
    private JCheckBox checkAbstract;
    private JCheckBox checkPalabrasClave;
    private JCheckBox checkKeywords;
    private JCheckBox checkPaginas;
    private JCheckBox checkNoActualizar;
    
    private static String v_doi_art="";
    private static String v_num_art="";
    private static String v_trans_title="";
    private static String v_trans_title2="";
    private static String v_title="";
    private static String v_seccion="";
    private static String v_abstract="";
    private static String v_trans_abstract="";
    private static String v_resumo="";
    private static String v_lang_sec="";
    private static String v_lang_sec2="";
    private static String v_lang="";
    private static String v_fpage="";
    private static String v_lpage="";
    private static String v_elocation_id="";
    private static String v_volume="";
    private static String v_issue="";
    private static String v_pubdate="";
    private static String v_receiveddate="";
    private static String v_accepteddate="";
    private static String v_palabras_clave="";
    private static String v_keywords="";
    private static String v_palabras_chave="";
    private static String v_autores="";
    private static String v_autores_notag="";
    private static String v_autores_notag_inv="";
    private static String v_autores_bio="";
    private static String v_afiliaciones="";
    private static String v_journalTitle="";
    private static String v_prefijo ="";
    private static String v_ruta="C:\\jats\\";
    private int contador=0;
    private static Hashtable <String, String>rutas = new Hashtable <String, String> ();
    private static Hashtable <String, String>rutasURL = new Hashtable <String, String> ();
    private static Hashtable <String, String>rutasURL1 = new Hashtable <String, String> ();
    private static Hashtable <String, String>rutasTablas = new Hashtable <String, String> ();
    private static Hashtable <String, String>nomrevista = new Hashtable <String, String> ();
    private String fichero_temporal="";
    private String fichero_htm="";
    private Document doc;
    private String fichero_sql="";
    private String fic_salida_epub="";
    private String subject="";
    private String keywords="";
    private String fic_salida_pdf="";
    private String nomb_fic_salida_pdf="";
    private static String fichero_referencias = "";
    private static String v_correspondencias_nombres ="";
    private static String v_contentExtended ="";
    private static String hoy="";
    private static String v_copyright_year="";
    
    // Librería de MySQL
    private String driver = "com.mysql.cj.jdbc.Driver";
    // Nombre de la base de datos
    private String database = "mydatabase";
    // Host
    private String hostname = "myhostname";
    // Puerto
    private String port = "myport";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?characterEncoding=UTF-8&useSSL=false&serverTimezone="+TimeZone.getDefault().getID();
    // Nombre de usuario
    private String username = "myusername";
    // Clave de usuario
    private String password = "mypassword";
    
    private String userFTP = "myuserftp";
    private String passFTP = "mypasswordftp";
    
    private String id_articulo="";
    private String id_numero="";
    private String id_volumen="";
    private String id_revista="";
    private static Timer timer=null;
    
    private File ficheroLog=null;
    private int iterador=0;
    
    private static String strNotes="";
    
    private static HashMap <String, String> notas_ref= new HashMap<String, String>();
    
    JComboBox<String> comboBox;
    
    public JATSProcessor() {
        super();                    
        configurarVentana();        
        inicializarComponentes();   
    }
    
    private void configurarVentana() {
        this.setTitle("Transforma JATS");                   
        this.setSize(610, 310);                             
        this.setLocationRelativeTo(null);                   
        this.setLayout(null);                               
        this.setResizable(false);                           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        // creamos los componentes
    	
    	rutas.put("XXXXX-XXXXX","myjournal");
    	rutasURL.put("XXXXX-XXXXX","https://www.myjournalwebpage.com");    	
    	rutasURL1.put("myjournal","https://www.myjournalwebpage.com");
    	rutasTablas.put("XXXXX-XXXXX","revista_");
    	
    	nomrevista.put("XXXXX-XXXXX","My Journal Name");
    	
    	String labels[] = {"seleccione carpeta", "myjournal"};
    	
    	comboBox = new JComboBox<String>(labels);
    	
    	setBounds(100, 100, 630, 680);
        getContentPane().setLayout(null);

        area = new JTextArea();
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(10, 11, 455, 449);

        getContentPane().add(scroll);
        setLocationRelativeTo ( null );
        
        boton = new JButton();
        boton.setText("Empezar");   
        boton.setBounds(470, 61, 150, 30);  
        boton.addActionListener(this);      
        
        botonSelect = new JButton();
        botonSelect.setText("Seleccionar");  
        botonSelect.setBounds(470, 11, 150, 30);  
        botonSelect.addActionListener(this);   
    	
        
        botonActualizaHead = new JButton();
        botonActualizaHead.setText("Actualiza Meta Head");  
        botonActualizaHead.setBounds(470, 111, 150, 30);  
        botonActualizaHead.addActionListener(this);
        
        botonSubefic = new JButton();
        botonSubefic.setText("Sube ficheros FTP"); 
        botonSubefic.setBounds(470, 161, 150, 30); 
        botonSubefic.addActionListener(this);
        
        botonChequea = new JButton();
        botonChequea.setText("Chequea");   
        botonChequea.setBounds(470, 211, 150, 30);  
        botonChequea.addActionListener(this);
        
        botonGenDOI= new JButton();
        botonGenDOI.setText("Genera DOI");   
        botonGenDOI.setBounds(470, 261, 150, 30);  
        botonGenDOI.addActionListener(this);
        
        botonGenDOAJ= new JButton();
        botonGenDOAJ.setText("Genera DOAJ");   
        botonGenDOAJ.setBounds(470, 311, 150, 30);  
        botonGenDOAJ.addActionListener(this);
        
        
        botonSubirOtros= new JButton();
        botonSubirOtros.setText("Genera excel Referencias");   
        botonSubirOtros.addActionListener(this);
        
        botonSubirOtros1= new JButton();
        botonSubirOtros1.setText("Eliminar refs");   
        botonSubirOtros1.addActionListener(this);
        
        JPanel panelAux = new JPanel();
        panelAux.setBackground(Color.LIGHT_GRAY);
        panelAux.setBounds(468, 364, 160, 100);
        comboBox.setBounds(5, 5, 140, 30);
        botonSubirOtros.setBounds(5, 70, 150, 30);
        botonSubirOtros1.setBounds(5, 70, 180, 30);
        panelAux.add(botonSubirOtros);
        panelAux.add(botonSubirOtros1);
        
        JLabel texto = new JLabel();
        texto.setText("Si deseas que NO se actualice algún valor márcalo");
        texto.setBounds(20, 470, 300, 30);
        
        
        
        checkTitulo = new JCheckBox();
        checkTitulo.setBounds(20, 500, 100, 30);
        checkTitulo.setText("título");
        
        checkTituloEn = new JCheckBox();
        checkTituloEn.setBounds(20, 525, 100, 30);
        checkTituloEn.setText("título eng");
        
        checkAutores = new JCheckBox();
        checkAutores.setBounds(20, 550, 100, 30);
        checkAutores.setText("autores");
        
        checkAfiliacion = new JCheckBox();
        checkAfiliacion.setBounds(20, 575, 100, 30);
        checkAfiliacion.setText("afiliación");
        
        checkResumen = new JCheckBox();
        checkResumen.setBounds(20, 600, 100, 30);
        checkResumen.setText("resumen");
        
        checkAbstract = new JCheckBox();
        checkAbstract.setBounds(200, 500, 100, 30);
        checkAbstract.setText("abstract");
        
        checkPalabrasClave = new JCheckBox();
        checkPalabrasClave.setBounds(200, 525, 150, 30);
        checkPalabrasClave.setText("palabras clave");
        
        checkKeywords = new JCheckBox();
        checkKeywords.setBounds(200, 550, 100, 30);
        checkKeywords.setText("keywords");
        
        checkPaginas = new JCheckBox();
        checkPaginas.setBounds(200, 575, 100, 30);
        checkPaginas.setText("páginas");
        
        checkCorrespondencia = new JCheckBox();
        checkCorrespondencia.setBounds(200, 600, 150, 30);
        checkCorrespondencia.setText("correspondencia");
        
        checkNoActualizar = new JCheckBox();
        checkNoActualizar.setBounds(400, 500, 200, 30);
        checkNoActualizar.setText("NO ACTUALIZAR HTML");
        
        
        this.add(boton);
        this.add(botonSelect);
        this.add(botonActualizaHead);
        this.add(botonSubefic);
        this.add(botonChequea);
        this.add(botonGenDOI);
        this.add(botonGenDOAJ);
        this.add(texto);
        this.add(checkTituloEn);
        this.add(checkTitulo);
        this.add(checkAutores);
        this.add(checkAfiliacion);
        this.add(checkResumen);
        this.add(checkAbstract);
        this.add(checkPalabrasClave);
        this.add(checkKeywords);
        this.add(checkPaginas);
        this.add(checkCorrespondencia);
        this.add(checkNoActualizar);
        this.add(panelAux);
        
        
        botonActualizaHead.setVisible(false);
        botonSubefic.setVisible(false);
        botonChequea.setVisible(false);
        botonGenDOI.setVisible(true);
        botonGenDOAJ.setVisible(true);
        
        this.add(scroll);
        
        GregorianCalendar calendar = new GregorianCalendar();
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int msec = calendar.get(Calendar.MILLISECOND);

		String strAno = String.valueOf(ano);
		String strMes = ( mes>9 )?String.valueOf(mes):"0"+String.valueOf(mes);
		String strDia = ( dia>9 )?String.valueOf(dia):"0"+String.valueOf(dia);
		String strHora = ( hora>9 )?String.valueOf(hora):"0"+String.valueOf(hora);
		String strMin = ( min>9 )?String.valueOf(min):"0"+String.valueOf(min);
		String strSec = ( sec>9 )?String.valueOf(sec):"0"+String.valueOf(sec);
		String strMsec = ( msec>99 )?String.valueOf(msec):(msec>9)?"0"+String.valueOf(msec):"00"+String.valueOf(msec);
		
		hoy=strAno+"-"+strMes+"-"+strDia;
        
        String ct1 = strAno + strMes + strDia + strHora + strMin + strSec + strMsec;
        
        ficheroLog = new File("C:\\jats\\"+ct1+"_errores.txt");
        
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        
        FileFilter filtro=new FileFilter() {
        	   public String getDescription() {
        	       return "XML (*.xml)";
        	   }

        	   public boolean accept(File f) {
        	       if (f.isDirectory()) {
        	           return true;
        	       } else {
        	           String filename = f.getName().toLowerCase();
        	           return filename.toLowerCase().endsWith(".xml") ;
        	       }
        	   }
        	};
        	
        jfc.setFileFilter(filtro);

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			iterador++;
			File selectedFile = jfc.getSelectedFile();
			area.setText("fichero seleccionado: "+selectedFile.getAbsolutePath());
			area.setText(area.getText()+"\n\n"+"Pulse 'EMPEZAR' ---->");
			fichero=selectedFile.getAbsolutePath();
			nomBfichero=selectedFile.getName();
			//System.out.println(selectedFile.getAbsolutePath());
		}
    }

    public void actionPerformed(ActionEvent e){
    	/*inicializamos todas las variables por si ya procesamos otro fichero antes*/
    	strIndexSec = "";
    	strIndexFig = "";
    	strIndexTable = "";
    	strIndexEquation = "";
    	strIndexRef = "";
    	
    	volume = "";
    	issue = "";
    	fPage = "";
    	lPage = "";
    	pubDate = "";
    	v_num_art = "";
    	journalTitle = "";
    	publisherName = "";
    	
    	figCount = "";
    	tableCount = "";
    	equationCount = "";
    	refCount = "";
    	pageCount = "";
    	
    	otherQuery = "";
    	
    	ficherosSubir=new ArrayList<String[]>();
    	dois_referencias=new ArrayList<String[]>();
    	
        v_trans_title="";
        v_trans_title2="";
        v_title="";
        v_seccion="";
        v_abstract="";
        v_trans_abstract="";
        v_lang_sec="";
        v_lang="";
        v_fpage="";
        v_lpage="";
        v_volume="";
        v_elocation_id="";
        v_issue="";
        v_pubdate="";
        v_receiveddate="";
        v_accepteddate="";
        v_palabras_clave="";
        v_keywords="";
        v_palabras_chave="";
        v_resumo="";
        v_autores="";
        v_autores_notag="";
        v_autores_notag_inv="";
        v_autores_bio="";
        v_afiliaciones="";
        v_journalTitle="";
        v_prefijo ="";
        v_ruta="C:\\jats\\";
        contador=0;
        fichero_temporal="";
        fichero_htm="";
        fichero_sql="";
        subject="";
        keywords="";
        fichero_referencias = "";
        v_contentExtended = "";
        v_copyright_year="";
    	
        id_articulo="";
        id_numero="";
        id_volumen="";
        id_revista="";
        v_correspondencias_nombres="";
        strNotes="";
        
        
        GregorianCalendar calendar = new GregorianCalendar();
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int msec = calendar.get(Calendar.MILLISECOND);

		String strAno = String.valueOf(ano);
		String strMes = ( mes>9 )?String.valueOf(mes):"0"+String.valueOf(mes);
		String strDia = ( dia>9 )?String.valueOf(dia):"0"+String.valueOf(dia);
		String strHora = ( hora>9 )?String.valueOf(hora):"0"+String.valueOf(hora);
		String strMin = ( min>9 )?String.valueOf(min):"0"+String.valueOf(min);
		String strSec = ( sec>9 )?String.valueOf(sec):"0"+String.valueOf(sec);
		String strMsec = ( msec>99 )?String.valueOf(msec):(msec>9)?"0"+String.valueOf(msec):"00"+String.valueOf(msec);

		final String ct = strAno + strMes + strDia + strHora + strMin + strSec + strMsec;
		//20190227094536000
		HashMap<String,String> reemplazar = new HashMap<String,String>();
		reemplazar.put("1122334455", ct);
		
		if(e.getSource().equals(botonSubirOtros1) ){
			contador=1000;
    		if(timer!=null)
    			timer.stop();
    		try{
    			File fichero_original = new File(this.fichero);
    			nombre_original = fichero_original.getName();
				fichero_temporal = v_ruta + nombre_original.substring(0, nombre_original.lastIndexOf('.')) + "_noref" + nombre_original.substring(nombre_original.lastIndexOf('.'));

		        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        Document document = db.parse(fichero_original);
		        document.getDocumentElement().normalize();
		        System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
		        NodeList nList = document.getElementsByTagName("mixed-citation");
		        System.out.println("----------------------------"+nList.getLength());
		        for (int temp = nList.getLength()-1; temp > -1; temp--) {
		        	Node elimina=nList.item(temp);
		        	elimina.getParentNode().removeChild(elimina);
	            }
		        guardaConFormato(document,fichero_temporal);
	    		
    		}
	    	catch(Exception ex){
	    		try {
					throw new MiExcepcion(ficheroLog, ex.getMessage());
				} catch (MiExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
		}
		
		if(e.getSource().equals(botonSubirOtros) ){
			contador=1000;
    		if(timer!=null)
    			timer.stop();
    		try{
    			File fichero_original = new File(this.fichero);
    			nombre_original = fichero_original.getName();
				fichero_temporal = v_ruta + nombre_original.substring(0, nombre_original.lastIndexOf('.')) + "_temp" + nombre_original.substring(nombre_original.lastIndexOf('.'));
			    fichero_sql = v_ruta + nombre_original.replace(".xml", ".sql");
			    fichero_htm = v_ruta + nombre_original.replace(".xml", ".htm");
			    fic_salida_pdf=v_ruta + nombre_original.substring(0, nombre_original.indexOf(".xml"))+".pdf";
			    nomb_fic_salida_pdf=nombre_original.substring(0, nombre_original.indexOf(".xml"))+".pdf";
			    fichero_referencias = v_ruta + nombre_original.substring(0, nombre_original.indexOf(".xml"))+"_referencias.xlsx";
			    nomBfichero=this.fichero;
			    prepararFichero(nomBfichero,v_ruta + nombre_original, ficherosSubir);
	    		doc=procesarFicheroTemporal(fichero_temporal, fichero_htm);
	    		String[] retorno = procesarDocReferencias(doc, fichero_sql, dois_referencias, ficherosSubir, subject, keywords);
			    
			    generamosMetadatos(fic_salida_pdf, nomBfichero, subject, keywords, ficherosSubir, dois_referencias,ficheroLog);
			    area.setText(area.getText()+"\n\n"+"Termindo de procesar referencias ");
	    		/*JFileChooser jfc1 = new JFileChooser();
	    		jfc1.setMultiSelectionEnabled(true);
	    		int returnValue = jfc1.showOpenDialog(null);
	    		if (returnValue == JFileChooser.APPROVE_OPTION) {
	    			String valor_carpeta = (String)comboBox.getSelectedItem();
	    			if(valor_carpeta.equalsIgnoreCase("seleccione carpeta")){
	    				new AboutDialog(this, "error!!", "error: debe seleccionar una carpeta");
	    			}else{
	    				String carpeta="revistas/";
	    				if (valor_carpeta.equalsIgnoreCase("clinicacontemporanea")){
	    					carpeta="";
	    				}
	    				File[] files = jfc1.getSelectedFiles();
	    				for (int i=0;i<files.length;i++){
	    					//System.out.println(files[i].getAbsolutePath());
	    					File ficheroEntrada=files[i];
	    		        	if((ficheroEntrada.getName().toLowerCase().endsWith(".jpg") ||
	    		        		ficheroEntrada.getName().toLowerCase().endsWith(".jpeg") ||
	    		        		ficheroEntrada.getName().toLowerCase().endsWith(".gif") ||
	    		        		ficheroEntrada.getName().toLowerCase().endsWith(".bmp") ||
	    		        		ficheroEntrada.getName().toLowerCase().endsWith(".png") || 
	    		        		ficheroEntrada.getName().toLowerCase().endsWith(".tif") ||
	    		        		ficheroEntrada.getName().toLowerCase().endsWith(".tiff"))){
	    		        		String targetfile="/web/"+carpeta+valor_carpeta+"/jats_files/"+ficheroEntrada.getName();
	    		        		SubeFicheroFTP(ficheroEntrada, targetfile, area);
	    		        	}
	    		        	else if(ficheroEntrada.getName().toLowerCase().endsWith(".pdf") ||
	        		        	ficheroEntrada.getName().toLowerCase().endsWith(".epub")){
	        		        	String targetfile="/web/"+carpeta+valor_carpeta+"/archivos/"+ficheroEntrada.getName();
	        		        	SubeFicheroFTP(ficheroEntrada, targetfile, area);
	        		        }
	    		        	else if(ficheroEntrada.getName().toLowerCase().endsWith(".xml")){
	            		        String targetfile="/web/"+carpeta+valor_carpeta+"/intranet/jats/"+ficheroEntrada.getName();
	            		        SubeFicheroFTP(ficheroEntrada, targetfile, area);
	            		    }
	    		        	else {
	    		        		area.setText(area.getText()+"\n\n"+"No se reconoce el tipo para una carpeta!! "+ficheroEntrada.getName());
	    		        		throw new MiExcepcion(ficheroLog, "Error al subir fichero---> No se reconoce el tipo para una carpeta!! "+ficheroEntrada.getName());
	    		        	}
	    				}
	    			}
	    		}*/
    		}
	    	catch(Exception ex){
	    		try {
					throw new MiExcepcion(ficheroLog, ex.getMessage());
				} catch (MiExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
		}
		
		if(e.getSource().equals(botonGenDOI) ){
			contador=1000;
    		if(timer!=null)
    			timer.stop();
    		String ruta_url=rutasURL.get(v_issn);
    		String carpeta =(String)comboBox.getSelectedItem();
    		Boolean borrar=false;
    		if(true){
    			borrar=true;
    			/*if(carpeta.equalsIgnoreCase("seleccione carpeta")){
    				new AboutDialog(this, "error!!", "error: debe seleccionar una carpeta");
    				ruta_url="";
    			}else
    				ruta_url=rutasURL1.get(carpeta);*/
    			/*tenemos que recuperar el doi del fichero jats*/
    			try{
	    			InputStream inputStream= new FileInputStream(this.fichero);
	    	        Reader reader = new InputStreamReader(inputStream,"UTF-8");
	    	        InputSource is = new InputSource(reader);
	    	        is.setEncoding("UTF-8");
	    	
	    	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	        Document doc = dBuilder.parse(is);
	    	        
	    	        doc.getDocumentElement().normalize();
	    			//System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
	    			NodeList nList = doc.getElementsByTagName("issn");
	    			
	    			if( nList.getLength()==0 )
	    			{
	    				System.out.println("*** WARNING!!! No hay nodo issn");
	    			}
	    			else
	    			{
	    				for (int temp = 0; temp < nList.getLength(); temp++)
	    				{
	    					Node nNode = nList.item(temp);
	    					NamedNodeMap atts = nNode.getAttributes();
	    					for (int temp1 = 0; temp1 < atts.getLength(); temp1++)
	    					{
	    						Node att = atts.item(temp1);
	    						if( att.getNodeName().equalsIgnoreCase("pub-type") && (att.getNodeValue().equals("ppub")||att.getNodeValue().equals("epub")))
	    						{
	    							if(v_issn==null||v_issn.equals(""))
	    								v_issn = nNode.getTextContent();
	    						}
	    					}
	    					
	    				}
	    			}
	    			
	    			nList = doc.getElementsByTagName("article-id");
	    			if( nList.getLength()==0 )
	    			{
	    				System.out.println("*** WARNING!!! No hay nodo article-id");
	    			}
	    			else
	    			{
	    				for (int temp = 0; temp < nList.getLength(); temp++)
	    				{
	    					Node nNode = nList.item(temp);
	    					NamedNodeMap atts = nNode.getAttributes();
	    					for (int temp1 = 0; temp1 < atts.getLength(); temp1++)
	    					{
	    						Node att = atts.item(temp1);
	    						if( att.getNodeName().equalsIgnoreCase("pub-id-type") && att.getNodeValue().equals("doi"))
	    						{
	    							v_doi_art = nNode.getTextContent();
	    						}else {
	    							if( att.getNodeName().equalsIgnoreCase("pub-id-type") && att.getNodeValue().equals("other"))
		    						{
		    							v_num_art = nNode.getTextContent();
		    						}
	    						}
	    					}
	    					
	    				}
	    			}
	    			ruta_url=rutasURL.get(v_issn);
	    			
	    	        /*
	    	        Node node_article=doc.getFirstChild();
	    	        Node nodo_front=node_article.getTextContent()getFirstChild();
	    	        //Node nodo_front=getNodoHijo(node_article, "front", null, null);
	    	        Node meta = getNodoHijo(nodo_front, "journal-meta", null, null);
	    	        Node node_issn = getNodoHijo(meta, "issn", "pub-type", "ppub");
	    	        
	    	        Node article=getNodoHijo(nodo_front, "article-meta", null, null);
	    	        Node nodo_doi=getNodoHijo(article, "article-id", "pub-id-type", "doi");
	    	        v_issn=node_issn.getTextContent();
	    	        ruta_url=rutasURL.get(v_issn);
	    	        v_doi_art=nodo_doi.getTextContent();*/
    			}catch (Exception exx){
    				exx.printStackTrace();
    			}
    			
    		}
    		if(ruta_url.endsWith("/"))
    			ruta_url=ruta_url.substring(0, ruta_url.length()-1);
    		if(ruta_url!=null && !ruta_url.equals("")){
				String url = ruta_url+"/art/"+ v_doi_art.substring(v_doi_art.lastIndexOf("/")+1);			
				reemplazar.put("url_articulo", url);
				reemplazar.put("xmlns=\"https://www.crossref.org/schema/crossref4.4.0.xsd\"","");
				String fichero_doi = v_ruta + "doi_"+ct+".xml";
				transformar("https://journals.copmadrid.org/jats_to_doi.xsl",this.fichero,fichero_doi, reemplazar);
				/*ya hemos creado el fichero del doi, ahora lo subimos al directorio /intranet/doi para invocar la url de subida*/
				try{
					String ruta_carpeta=rutas.get(v_issn);
					if(ruta_carpeta==null){
						ruta_carpeta = carpeta;
					}
					if(v_issn.equalsIgnoreCase("2530-3910")) {
						ruta_carpeta="deporte";
	    			}
	    			FTPClient ftpClient = new FTPClient();
	    			FileInputStream fis = null;
	    			File fic = new File(fichero_doi);
	    			
	    			String targetfile="/web/revistas/"+ruta_carpeta+"/intranet/doi/"+"doi_"+ct+".xml";
	    			
	    			if(v_issn.equals("1989-9912")) {
	    				targetfile="/web/clinicacontemporanea/intranet109/doi/"+"doi_"+ct+".xml";
	    			}
	    			try {
	    				ftpClient.connect(hostname);
	    				ftpClient.login(userFTP, passFTP);
	    			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	    			    String localfile=fic.getAbsolutePath();
	    			    fis = new FileInputStream(localfile);
	    			    ftpClient.deleteFile(targetfile.replace(".", "_bak."));
	    			    ftpClient.rename(targetfile, targetfile.replace(".", "_bak."));
	    	            File firstLocalFile = new File(localfile);
	    	 
	    	            String firstRemoteFile = targetfile;
	    	            InputStream inputStream = new FileInputStream(firstLocalFile);
	    	 
	    	            //System.out.println("Start uploading first file");
	    	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	    	            inputStream.close();
	    	            if (done) {
	    	            	area.setText(area.getText()+"\n\n"+"subido el fichero del DOI"+targetfile);
	    	            }
	    	            ftpClient.logout();
	    	            
	    	            /*ahora invocamos a la página que se lo pasa a crossref*/
	    	            
	    	            String url_subir="https://www.copmadrid.es/web/revistas/"+ruta_carpeta+"/intranet/doi/doi_upload.php?nomb_fic="+"doi_"+ct+".xml";
	    	            if(v_issn.equals("1989-9912")) {
	    	            	url_subir="https://www.copmadrid.es/web/clinicacontemporanea/intranet109/doi/doi_upload.php?nomb_fic="+"doi_"+ct+".xml";
		    			}
	    	            	
		            	if(Desktop.isDesktopSupported()){
		                    Desktop desktop = Desktop.getDesktop();
		                    try {
		                        desktop.browse(new URI(url_subir));
		                    } catch (Exception  ex) {
		                        // TODO Auto-generated catch block
		                        ex.printStackTrace();
		                    }
		                }else{
		                    Runtime runtime = Runtime.getRuntime();
		                    try {
		                        runtime.exec("xdg-open " + url_subir);
		                    } catch (IOException ex) {
		                        // TODO Auto-generated catch block
		                        ex.printStackTrace();
		                    }
		                }
		                area.setText(area.getText()+"\n\n"+"abrimos la página para crossref: "+url_subir);
	    	            	
	        			} catch (IOException ex) {
	        				area.setText(area.getText()+"\n\n"+"error al subir el fichero del DOI ");
			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
			    			area.setText(area.getText()+"\n***ERROR!!***"+ex.getMessage());
			    			throw new MiExcepcion(ficheroLog, "Error al subir fichero del DOI---> "+ex.getMessage());
	        			} finally {
	        			    try {
	        			        if (fis != null) {
	        			            fis.close();
	        			        }
	        			        ftpClient.disconnect();
	        			    } catch (IOException exc) {
	        			    	area.setText(area.getText()+"\n\n"+"error al subir el fichero deL DOI ");
				    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
				    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
				    			throw new MiExcepcion(ficheroLog, "Error al subir fichero del DOI---> "+exc.getMessage());
	        			    }
	        			}
	    		}
				catch(MiExcepcion ex){}
	    		catch(Exception exc){
	    			//exc.printStackTrace();
	    			area.setText(area.getText()+"\n\n"+"error al subir el fichero del doi ");
	    			area.setText(area.getText()+"\n\n"+"en el directorio intranet/doi");
	    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
	    			try {
						throw new MiExcepcion(ficheroLog, "Error al subir fichero del DOI---> "+exc.getMessage());
					} catch (MiExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
				if(borrar){
					v_issn="";
					v_doi_art="";
				}
    		}
		}
		
		if(e.getSource().equals(botonGenDOAJ) ){
			contador=1000;
			String idioma_pre="eng";
    		if(timer!=null)
    			timer.stop();
    		String ruta_url=rutasURL.get(v_issn);
    		try{
    			InputStream inputStream= new FileInputStream(this.fichero);
    	        Reader reader = new InputStreamReader(inputStream,"UTF-8");
    	        InputSource is = new InputSource(reader);
    	        is.setEncoding("UTF-8");
    	
    	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	        Document doc = dBuilder.parse(is);
    	        
    	        doc.getDocumentElement().normalize();
    			//System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
    			NodeList nList = doc.getElementsByTagName("issn");
    			
    			if( nList.getLength()==0 )
    			{
    				System.out.println("*** WARNING!!! No hay nodo issn");
    			}
    			else
    			{
    				for (int temp = 0; temp < nList.getLength(); temp++)
    				{
    					Node nNode = nList.item(temp);
    					NamedNodeMap atts = nNode.getAttributes();
    					for (int temp1 = 0; temp1 < atts.getLength(); temp1++)
    					{
    						Node att = atts.item(temp1);
    						if( v_issn.equals("") && att.getNodeName().equalsIgnoreCase("pub-type") && (att.getNodeValue().equals("ppub")||att.getNodeValue().equals("epub")))
    						{
    							v_issn = nNode.getTextContent();
    						}
    					}
    					
    				}
    			}
    			
    			nList = doc.getElementsByTagName("article-id");
    			if( nList.getLength()==0 )
    			{
    				System.out.println("*** WARNING!!! No hay nodo article-id");
    			}
    			else
    			{
    				for (int temp = 0; temp < nList.getLength(); temp++)
    				{
    					Node nNode = nList.item(temp);
    					NamedNodeMap atts = nNode.getAttributes();
    					for (int temp1 = 0; temp1 < atts.getLength(); temp1++)
    					{
    						Node att = atts.item(temp1);
    						if( att.getNodeName().equalsIgnoreCase("pub-id-type") && att.getNodeValue().equals("doi"))
    						{
    							v_doi_art = nNode.getTextContent();
    						}
    					}
    				}
    			}
    			ruta_url=rutasURL.get(v_issn);
    			nList = doc.getElementsByTagName("trans-title-group");
    			if( nList.getLength()==0 )
    			{
    				System.out.println("*** WARNING!!! No hay nodo trans-title-group");
    			}
    			else
    			{
    				if(nList.getLength()>0)
    				{
    					Node nNode = nList.item(0);
    					v_lang_sec = buscarAtributo(nNode,"xml:lang");
    					if(v_lang_sec.equals("es"))
							idioma_pre="eng";
						else
							idioma_pre="spa";
    					
						/*if(v_lang_sec.equals("en"))
							idioma_pre="spa";
						else
							idioma_pre="eng";*/
    				}
				}
    	        /*
    	        Node node_article=doc.getFirstChild();
    	        Node nodo_front=node_article.getTextContent()getFirstChild();
    	        //Node nodo_front=getNodoHijo(node_article, "front", null, null);
    	        Node meta = getNodoHijo(nodo_front, "journal-meta", null, null);
    	        Node node_issn = getNodoHijo(meta, "issn", "pub-type", "ppub");
    	        
    	        Node article=getNodoHijo(nodo_front, "article-meta", null, null);
    	        Node nodo_doi=getNodoHijo(article, "article-id", "pub-id-type", "doi");
    	        v_issn=node_issn.getTextContent();
    	        ruta_url=rutasURL.get(v_issn);
    	        v_doi_art=nodo_doi.getTextContent();*/
			}catch (Exception exx){
				exx.printStackTrace();
			}
    		String carpeta =(String)comboBox.getSelectedItem();
    		if(ruta_url==null){
    			if(carpeta.equalsIgnoreCase("seleccione carpeta")){
    				new AboutDialog(this, "error!!", "error: debe seleccionar una carpeta");
    				ruta_url="";
    			}else
    				ruta_url=rutasURL1.get(carpeta);
    		} 
    		if(ruta_url!=null && !ruta_url.equals("")){
    			String query="select archivo from revista_articulo where doi like '%"+v_doi_art+"'";
    			Connection conn = conectarMySQL();
    			PreparedStatement pstm;
				try {
					pstm = conn.prepareStatement(query);
					ResultSet rs=pstm.executeQuery();
	    			if(rs.next()){
	    				nomb_fic_salida_pdf = rs.getString(1);
	    			}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
				String url = ruta_url+"/art/"+ v_doi_art.substring(v_doi_art.lastIndexOf("/")+1);
				String url_doaj = ruta_url+"/archivos/"+ nomb_fic_salida_pdf;
				if(v_issn.equalsIgnoreCase("1989-9912") || v_issn.equalsIgnoreCase("2530-3910")) {
					reemplazar.put("<eissn/>","");
					reemplazar.put("<issn/>","");
					reemplazar.put("<issn>","<eissn>");
					reemplazar.put("</issn>","</eissn>");
				}
				reemplazar.put("url_articulo", url);
				/* cambiamos la ruta del pdf porque ahora doaj nos deja meter la ruta del html
				reemplazar.put("url_pdf", url_doaj);*/
				reemplazar.put("url_pdf", url);
				reemplazar.put("idioma_pre", idioma_pre);
				reemplazar.put("<title language=\"spa\"/>","");
				reemplazar.put("<title language=\"eng\"/>","");
				reemplazar.put("<abstract language=\"spa\"/>","");
				reemplazar.put("<abstract language=\"eng\"/>","");
				String fichero_doaj = v_ruta + "doaj_"+ct+".xml";
				transformar("https://journals.copmadrid.org/jats2doaj.xsl",this.fichero,fichero_doaj, reemplazar);
				area.setText(area.getText()+"\n\n"+"generado el fichero DOAJ--->DEBE SUBIRLO MANUALMENTE!!!");
				area.setText(area.getText()+"\n"+"El fichero se encuentra en C:\\jats\\"+v_ruta + "doaj_"+ct+".xml");
    		}

		}
    	
    	if(e.getSource().equals(botonSelect)){
    		contador=1000;
    		if(timer!=null)
    			timer.stop();
    		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    		
    		FileFilter filtro=new FileFilter() {
         	   public String getDescription() {
         	       return "XML (*.xml)";
         	   }

         	   public boolean accept(File f) {
         	       if (f.isDirectory()) {
         	           return true;
         	       } else {
         	           String filename = f.getName().toLowerCase();
         	           return filename.toLowerCase().endsWith(".xml") ;
         	       }
         	   }
         	};
         	
         	jfc.setFileFilter(filtro);

         	int returnValue = jfc.showOpenDialog(null);

         	if (returnValue == JFileChooser.APPROVE_OPTION) {
    			File selectedFile = jfc.getSelectedFile();
    			area.setText("fichero seleccionado: "+selectedFile.getAbsolutePath());
    			fichero=selectedFile.getAbsolutePath();
    			nomBfichero=selectedFile.getName();
    			//System.out.println(selectedFile.getAbsolutePath());
    		}
    	}
    	if(e.getSource().equals(botonChequea)){
    		contador=1000;
    		if(timer!=null)
    			timer.stop();
    		/*v_issn="1132-0559";
    		v_doi_art="10.5093/pi2019a3";*/
    		String url = rutasURL.get(v_issn)+"/art/"+ v_doi_art.substring(v_doi_art.lastIndexOf("/")+1);

            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(url));
                } catch (Exception  ex) {
                    //ex.printStackTrace();
                    try {
						throw new MiExcepcion(ficheroLog, "Error al abrir la página generada---> "+ex.getMessage());
					} catch (MiExcepcion e1) {
						e1.printStackTrace();
					}
                }
            }else{
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("xdg-open " + url);
                } catch (IOException ex) {
                	try {
						throw new MiExcepcion(ficheroLog, "Error al abrir la página generada---> "+ex.getMessage());
					} catch (MiExcepcion e1) {
						e1.printStackTrace();
					}
                }
            }
            area.setText(area.getText()+"\n\n"+"abrimos la página"+url);
    	}
    	if(e.getSource().equals(botonSubefic)){
    		contador=1000;
    		if(timer!=null)
    			timer.stop();
    		
    		String nomBSinExt = nomBfichero.substring(nomBfichero.lastIndexOf("\\")+1, nomBfichero.lastIndexOf("."));
    		File directorio_leer = new File(v_ruta+"\\jats_files");
    		//leemos los ficheros del directorio que son imagenes
    		if(directorio_leer.isDirectory())
    		{
    			for (final File ficheroEntrada : directorio_leer.listFiles()) {
    		        if (!ficheroEntrada.isDirectory()) {
    		        	String targetfile="/web/revistas/"+rutas.get(v_issn)+"/jats_files/"+ficheroEntrada.getName();
    		        	
    		        	if(v_issn.equals("1989-9912")) {
    	    				targetfile="/web/clinicacontemporanea/jats_files/"+ficheroEntrada.getName();
    	    			}
    		        	if(v_issn.equalsIgnoreCase("2530-3910")) {
    		        		targetfile="/web/revistas/deporte/jats_files/"+ficheroEntrada.getName();
    	    			}
    		        	
    		        	if(ficheroEntrada.getName().indexOf(nomBSinExt)!=-1 &&
    		        		(ficheroEntrada.getName().toLowerCase().endsWith(".jpg") ||
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".jpeg") ||
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".gif") ||
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".bmp") ||
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".png") || 
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".tif") ||
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".tiff"))){
    		        		try{
    		        			FTPClient ftpClient = new FTPClient();
    		        			FileInputStream fis = null;

    		        			try {
    		        				ftpClient.connect(hostname);
    		        				ftpClient.login(userFTP, passFTP);
    		        			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    		        			    String localfile=ficheroEntrada.getAbsolutePath();
    		        			    fis = new FileInputStream(localfile);
    		        			    ftpClient.deleteFile(targetfile.replace(".", "_bak."));
    		        			    ftpClient.rename(targetfile, targetfile.replace(".", "_bak."));
    		        	            File firstLocalFile = new File(localfile);
    		        	 
    		        	            String firstRemoteFile = targetfile;
    		        	            InputStream inputStream = new FileInputStream(firstLocalFile);
    		        	 
    		        	            //System.out.println("Start uploading first file");
    		        	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
    		        	            inputStream.close();
    		        	            if (done) {
    		        	            	area.setText(area.getText()+"\n\n"+"subido "+targetfile);
    		        	            }

    		        			    ftpClient.logout();
	    		        			} catch (IOException ex) {
	    		        				area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
	        			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
	        			    			area.setText(area.getText()+"\n***ERROR!!***"+ex.getMessage());
	        			    			throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+ficheroEntrada.getName()+ex.getMessage());
	    		        			} finally {
	    		        			    try {
	    		        			        if (fis != null) {
	    		        			            fis.close();
	    		        			        }
	    		        			        ftpClient.disconnect();
	    		        			    } catch (IOException exc) {
	    		        			    	area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
	    	    			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
	    	    			    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
	    	    			    			throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+ficheroEntrada.getName()+exc.getMessage());
	    		        			    }
	    		        			}
    			    		}
    			    		catch(Exception exc){
    			    			//exc.printStackTrace();
    			    			area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
    			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
    			    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
    			    			try {
									throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+ficheroEntrada.getName()+exc.getMessage());
								} catch (MiExcepcion e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
    			    		}
    		        	}
    		        }
    		    }
    		}
    		directorio_leer = new File(v_ruta+"\\archivo");
    		//leemos los ficheros del directorio que son imagenes
    		if(directorio_leer.isDirectory())
    		{
    			for (final File ficheroEntrada : directorio_leer.listFiles()) {
    		        if (!ficheroEntrada.isDirectory()) {
    		        	String targetfile="/web/revistas/"+rutas.get(v_issn)+"/archivos/"+ficheroEntrada.getName();
    		        	
    		        	if(v_issn.equals("1989-9912")) {
    	    				targetfile="/web/clinicacontemporanea/archivos/"+ficheroEntrada.getName();
    	    			}
    		        	if(v_issn.equalsIgnoreCase("2530-3910")) {
    		        		targetfile="/web/revistas/deporte/archivos/"+ficheroEntrada.getName();
    	    			}
    		        	if((ficheroEntrada.getName().indexOf(nomBSinExt)!=-1 || ficheroEntrada.getName().indexOf(nomBSinExt)!=-1) &&
    		        		(ficheroEntrada.getName().toLowerCase().endsWith(".pdf") ||
    		        		ficheroEntrada.getName().toLowerCase().endsWith(".epub") )){
    		        		try{
    		        			FTPClient ftpClient = new FTPClient();
    		        			FileInputStream fis = null;

    		        			try {
    		        				ftpClient.connect(hostname);
    		        				ftpClient.login(userFTP, passFTP);
    		        			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    		        			    String localfile=ficheroEntrada.getAbsolutePath();
    		        			    fis = new FileInputStream(localfile);
    		        			    ftpClient.deleteFile(targetfile.replace(".", "_bak."));
    		        			    ftpClient.rename(targetfile, targetfile.replace(".", "_bak."));
    		        	            File firstLocalFile = new File(localfile);
    		        	 
    		        	            String firstRemoteFile = targetfile;
    		        	            InputStream inputStream = new FileInputStream(firstLocalFile);
    		        	 
    		        	            //System.out.println("Start uploading first file");
    		        	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
    		        	            inputStream.close();
    		        	            if (done) {
    		        	            	area.setText(area.getText()+"\n\n"+"subido "+targetfile);
    		        	            }

    		        			    ftpClient.logout();
	    		        			} catch (IOException ex) {
	    		        				area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
	        			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
	        			    			area.setText(area.getText()+"\n***ERROR!!***"+ex.getMessage());
	        			    			throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+ficheroEntrada.getName()+ex.getMessage());
	    		        			} finally {
	    		        			    try {
	    		        			        if (fis != null) {
	    		        			            fis.close();
	    		        			        }
	    		        			        ftpClient.disconnect();
	    		        			    } catch (IOException exc) {
	    		        			    	area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
	    	    			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
	    	    			    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
	    	    			    			throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+ficheroEntrada.getName()+exc.getMessage());
	    		        			    }
	    		        			}
    			    		}
    			    		catch(Exception exc){
    			    			//exc.printStackTrace();
    			    			area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
    			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
    			    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
    			    			try {
									throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+ficheroEntrada.getName()+exc.getMessage());
								} catch (MiExcepcion e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
    			    		}
    		        	}
    		        }
    		    }
    		}
    		
    		/*y subimos el fichero original del jats*/
    		try{
    			FTPClient ftpClient = new FTPClient();
    			FileInputStream fis = null;
    			File fic = new File(this.fichero);
    			String targetfile="/web/revistas/"+rutas.get(v_issn)+"/intranet/jats/"+fic.getName();
    			if(v_issn.equals("1989-9912")) {
    				targetfile="/web/clinicacontemporanea/intranet109/jats/"+fic.getName();
    			}
    			try {
    				ftpClient.connect(hostname);
    				ftpClient.login(userFTP, passFTP);
    			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    			    String localfile=fic.getAbsolutePath();
    			    fis = new FileInputStream(localfile);
    			    ftpClient.deleteFile(targetfile.replace(".", "_bak."));
    			    ftpClient.rename(targetfile, targetfile.replace(".", "_bak."));
    	            File firstLocalFile = new File(localfile);
    	 
    	            String firstRemoteFile = targetfile;
    	            InputStream inputStream = new FileInputStream(firstLocalFile);
    	 
    	            //System.out.println("Start uploading first file");
    	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
    	            inputStream.close();
    	            if (done) {
    	            	area.setText(area.getText()+"\n\n"+"subido "+targetfile);
    	            }

    			    ftpClient.logout();
        			} catch (IOException ex) {
        				area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+fic.getName());
		    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
		    			area.setText(area.getText()+"\n***ERROR!!***"+ex.getMessage());
		    			throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+fic.getName()+ex.getMessage());
        			} finally {
        			    try {
        			        if (fis != null) {
        			            fis.close();
        			        }
        			        ftpClient.disconnect();
        			    } catch (IOException exc) {
        			    	area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+fic.getName());
			    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
			    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
			    			throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+fic.getName()+exc.getMessage());
        			    }
        			}
    		}
    		catch(Exception exc){
    			//exc.printStackTrace();
    			area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+fichero);
    			area.setText(area.getText()+"\n\n"+"en el directorio intrane/jats");
    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
    			try {
					throw new MiExcepcion(ficheroLog, "error al subir el fichero de "+fichero+exc.getMessage());
				} catch (MiExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    		
    	}
    	if(e.getSource().equals(botonActualizaHead)){
    		BufferedReader br = null;
    		contador=1000;
    		if(timer!=null)
    			timer.stop();
    		try
    		{
    			String fic_sql = v_ruta + "sql\\"+ nombre_original.replace(".xml", ".sql");
    			br = new BufferedReader(new InputStreamReader(new FileInputStream(fic_sql), "UTF8"));
    			String query;
    			Connection conn = conectarMySQL();
    			area.setText(area.getText()+"\n\n"+"leemos fichero para cabecera");
    			while ((query = br.readLine()) != null)
                {
    				if(!query.trim().equalsIgnoreCase("")){
    					PreparedStatement pstm = conn.prepareStatement(query);
    					String[] columnas = {"id_articulo_autor", "id_articulo_referencia"};
    					System.out.println(query);
    					int sid_articulo = pstm.executeUpdate(query, columnas);
    					ResultSet rs = pstm.getGeneratedKeys();

    				    if (rs.next()) {
    				    	sid_articulo = rs.getInt(1);
    				        //System.out.println(sid_articulo);
    				    } 
    				}
                }
    			br.close();
    			area.setText(area.getText()+"\n\n"+"escritos datos de cabecera");
    			area.setText(area.getText()+"\n\n"+"FIN!");
    		}
    		catch (Exception exc)
    		{
    			exc.printStackTrace();
    			area.setText(area.getText()+"\n\n"+"error al leer el fichero de consultas para HEAD");
    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
    			try {
					throw new MiExcepcion(ficheroLog, "error al leer el fichero de consultas para HEAD"+exc.getMessage());
				} catch (MiExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	}
		// TODO Auto-generated method stub
    	if(e.getSource().equals(boton)){
			try
			{
				issn = "";
		    	issn2 = "";
		    	v_issn = "";
		    	v_issn2 = "";
		    	v_doi_art="";
		    	v_trans_title="";
		    	fic_salida_epub="";
		    	fic_salida_pdf="";
		        nomb_fic_salida_pdf="";
				File fichero_original = new File(this.fichero);
				nombre_original = fichero_original.getName();
				fichero_temporal = v_ruta + nombre_original.substring(0, nombre_original.lastIndexOf('.')) + "_temp" + nombre_original.substring(nombre_original.lastIndexOf('.'));
			    fichero_sql = v_ruta + nombre_original.replace(".xml", ".sql");
			    fichero_htm = v_ruta + nombre_original.replace(".xml", ".htm");
			    fic_salida_pdf=v_ruta + nombre_original.substring(0, nombre_original.indexOf(".xml"))+".pdf";
			    nomb_fic_salida_pdf=nombre_original.substring(0, nombre_original.indexOf(".xml"))+".pdf";
			    fichero_referencias = v_ruta + nombre_original.substring(0, nombre_original.indexOf(".xml"))+"_referencias.xlsx";
			    
			    File fic_epub = new File(fichero.replace(".xml", ".epub"));
			    if(fic_epub.exists())
			    	fic_salida_epub=nombre_original.substring(0, nombre_original.indexOf(".xml"))+".epub";
			    else
			    	fic_salida_epub="";
			    
				area.setText(area.getText()+"\n\n"+"empezamos...");
				contador=0;
				nomBfichero=this.fichero;
				timer = new Timer(100, new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	contador++;
				    	if(contador==1){
				    		area.setText(area.getText()+"\n\n"+"preparamos el fichero...");
				    		prepararFichero(nomBfichero,v_ruta + nombre_original, ficherosSubir);
				    	}
				    	if(contador==2){
				    		area.setText(area.getText()+"\n\n"+"ya hemos preparado el fichero...");
				    		doc=procesarFicheroTemporal(fichero_temporal, fichero_htm);
				    		area.setText(area.getText()+"\n\n"+"creado el fichero temporal");
				    		area.setText(area.getText()+"\n\n"+"creado contenido HTML");
				    	}
				    	if(contador==3){
				    		area.setText(area.getText()+"\n\n"+"empezamos a procesar las referencias para cabecera");
				    		String[] retorno = procesarDocReferencias(doc, fichero_sql, dois_referencias, ficherosSubir, subject, keywords);
				    		subject=retorno[0];
				    		keywords=retorno[1];
				    	}
				    	if(contador==4){
				    		area.setText(area.getText()+"\n\n"+"terminado de procesar las referencias para cabecera");
				    		area.setText(area.getText()+"\n\n"+"empezamos a procesar las urls referencias");
				    		area.setText(area.getText()+"\n\n"+"sé paciente... este proceso puede tardar ;)");
				    		area.setText(area.getText()+"\n\n"+"........................................");
				    	}
				    	if(contador==5){
				    		String data = "empezamos a chequear las urls--->"+v_doi_art;
				    		escribeFicheroErrores(ficheroLog, data);
				    		
				    		File fic_or = new File(nomBfichero);
				    		if(!fic_or.exists()){
				    			area.setText(area.getText()+"\n\n"+"***NO SE ENCONTRÓ EL FICHERO PDF!!!");
				    		}else{
				    			try {
									generamosMetadatos(fic_salida_pdf, nomBfichero, subject, keywords, ficherosSubir, dois_referencias,ficheroLog);
								} catch (MiExcepcion e1) {
									// TODO Auto-generated catch block
									area.setText(area.getText()+"\n\n"+"ERROR!!!-->"+e1.getMessage());
								}
				    			area.setText(area.getText()+"\n\n"+"terminado de leer las referencias y extraído los emails");
				    			File fic_aux=new File(fichero_temporal);
				    			fic_aux.delete();
				    			fic_aux=new File(fichero_sql);
				    			fic_aux.delete();
				    			fic_aux=new File(fic_salida_pdf);
				    			fic_aux.delete();
				    			area.setText(area.getText()+"\n\n"+"Borrados ficheros temporales");
				    		}
				    		area.setText(area.getText()+"\n\n"+"........................................");
				    		area.setText(area.getText()+"\n\n"+"FINALIZADO EL PROCESO"); 
				    		area.setText(area.getText()+"\n\n"+"puedes chequear el resultado pulsando--->Chequea");
				    	}
				    	if(contador==6){
				    		/*miramos si el articulo estaba ya incluido en el sitemap y si no lo metemos*/
				    		try {
				    			FTPClient ftpClient = new FTPClient();
				    			FileInputStream fis = null;
				    			ftpClient.connect(hostname);
								ftpClient.login(userFTP, passFTP);
							    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
							    String carpeta = rutas.get(v_issn);
							    
							    String remoteFile1 = "/web/revistas/"+carpeta+"/sitemap.xml";
					            File downloadFile1 = new File("C:\\jats\\sitemap.xml");
					            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
					            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
					            outputStream1.close();
					 
					            if (success) {
					            	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(downloadFile1), "UTF8"));
					    			String linea="";
					            	String contenidoFichero="";
					    			area.setText(area.getText()+"\n\n"+"leemos fichero para cabecera");
					    			int i=0;
					    			while ((linea = br.readLine()) != null)
					                {
					    				if(!linea.trim().equalsIgnoreCase("")){
					    					if(i>0)
					    						contenidoFichero+=System.getProperty("line.separator")+linea;
					    					else
					    						contenidoFichero+=linea;
					    				}
					    				i++;
					                }
					    			br.close();
					    			String part_doi=v_doi_art.substring(v_doi_art.lastIndexOf("/"));
					    			if(contenidoFichero.indexOf(part_doi.trim()+"<")==-1) {
					    				String contenido_nuevo="<url><loc>https://journals.copmadrid.org/"+carpeta+"/art"+part_doi+"</loc><lastmod>"+hoy+"</lastmod><changefreq>daily</changefreq></url>";
					    				String part1=contenidoFichero.substring(0, contenidoFichero.indexOf("<url>"));
					    				String part2=contenidoFichero.substring(contenidoFichero.indexOf("<url>"));
					    				contenidoFichero=part1+contenido_nuevo+part2;
					    				
					    				File fic_nuevo= new File("C:\\jats\\sitemap1.xml");
					    				if(fic_nuevo.exists())
					    					fic_nuevo.delete();
					    				FileWriter flwriter = new FileWriter("C:\\jats\\sitemap1.xml", true);
					    				BufferedWriter bfwriter = new BufferedWriter(flwriter);
					    				bfwriter.write(contenidoFichero);
					    				bfwriter.close();
					    				
					    				//SubeFicheroFTP(fic_nuevo,remoteFile1, area);
					    				
					    			}else {
					    				System.out.println("ya estaba en el sitemap.xml");
					    			}
					            }
				    		}catch (Exception exx) {
				    			exx.printStackTrace();
				    		}
				            
				    		/*leemos el fichero de contenido que hemos creado*/
				    	    BufferedReader br = null;
				    		String contenidoHTML="";
				    		try
				    		{
				    			br = new BufferedReader(new InputStreamReader(new FileInputStream(fichero_htm), "UTF8"));
				    			String sCurrentLine;
				    			while ((sCurrentLine = br.readLine()) != null)
				                {
				    				contenidoHTML=contenidoHTML+sCurrentLine;
				                }
				    			br.close();
				    		}
				    		catch (IOException exc)
				    		{
				    			exc.printStackTrace();
				    			area.setText(area.getText()+"\n\n"+"error al leer el fichero HTML");
				    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
				    			try {
									throw new MiExcepcion(ficheroLog, "error al leer el fichero HTML"+exc.getMessage());
								} catch (MiExcepcion e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				    		}
				    		try
				    		{
				    			//si solo hay una afiliacion quitamos los superíndices
				    			if(	v_afiliaciones.lastIndexOf("<sup>")==v_afiliaciones.indexOf("<sup>")){
				    				v_autores=v_autores.replaceAll("<sup>1</sup>", "");
				    				v_afiliaciones=v_afiliaciones.replaceAll("<sup>1</sup>", "");
				    			}
				    			v_afiliaciones=v_afiliaciones.replaceAll(";;", ";");
				    			Connection conn = conectarMySQL();
				    			String query="select id_revista from revista where issn='"+v_issn+"'";
				    			PreparedStatement pstm = conn.prepareStatement(query);
				    			ResultSet rs=pstm.executeQuery();
				    			if(rs.next()){
				    				id_revista = rs.getString(1);
				    				if(id_revista.equals("8")){
				    					/*si es ejpalc cambiamos los titulos porque para esta revista esta al reves*/
				    					String tit_aux=v_title;
				    					v_title=v_trans_title;
				    					v_trans_title=tit_aux;
				    				}
				    			}
				    			String v_resumen="";
				    			String v_resumen_en="";
				    			String v_resumen_pt="";
				    			v_abstract=v_abstract.replaceAll("class=\"jats_p\"","style=\"text-align: justify;\"");
				    			v_trans_abstract=v_trans_abstract.replaceAll("class=\"jats_p\"","style=\"text-align: justify;\"");
				    			if(v_issn.equals("1132-0559")) {
				    				v_lang="en";
				    				v_lang_sec="es";
				    			}
				    			if(v_lang_sec.equals("en") || v_lang_sec.equalsIgnoreCase("")|| v_lang_sec.equals("pt"))
				    			{
				    				if(v_lang.equalsIgnoreCase(""))
				    					v_lang="es";
				    				v_resumen=v_abstract;
				    				v_resumen_en=v_trans_abstract;
				    				v_resumen_pt=v_resumo;
				    			}else
				    			{
				    				if(v_lang.equalsIgnoreCase(""))
				    					v_lang="en";
				    				v_resumen=v_trans_abstract;
				    				v_resumen_en=v_abstract;
				    				v_resumen_pt=v_resumo;
				    			}
				    			if(!v_resumen.equalsIgnoreCase("")){
				    				v_resumen="<p style=\"text-align: justify;\">"+v_resumen+"</p>";
				    			}
				    			if(!v_resumen_en.equalsIgnoreCase("")){
				    				v_resumen_en="<p style=\"text-align: justify;\">"+v_resumen_en+"</p>";
				    			}
				    			if(!v_resumen_pt.equalsIgnoreCase("")){
				    				v_resumen_pt="<p style=\"text-align: justify;\">"+v_resumen_pt+"</p>";
				    			}
				    			rs.close();
				    			pstm.close();
				    			String v_doi_art1=v_doi_art.replaceAll("https://doi.org/", "");
				    			
				    			/*para hacer pruebas*/
				    			//v_doi_art1="prueba";
				    			/*QUITAAAARRR*/
				    			
				    			String paginas=(v_fpage!=null && !v_fpage.equals(""))?v_fpage+" - "+v_lpage:"";
				    			if(paginas.equals("000 - 000") || paginas.equals("00 - 00")){
				    				paginas="";
				    			}
				    			String orden=(v_fpage!=null && !v_fpage.equals(""))?v_fpage:"0";
				    			if(!v_elocation_id.equalsIgnoreCase("")) {
				    				if(!paginas.equalsIgnoreCase(""))
				    					paginas=v_elocation_id+", " +paginas;
				    				else
				    					paginas=v_elocation_id;
				    				if(v_lang.equals("es"))
				    					paginas="Artículo "+paginas;
				    				else
				    					paginas="Article "+paginas;
				    				String ord=v_elocation_id.replace("e", "");
				    				int valor_ord=100-Integer.parseInt(ord.trim());
				    				valor_ord=Integer.parseInt(ord.trim());
				    				orden=String.valueOf(valor_ord);
				    			}
				    			
				    			String pag=paginas;
				    			String texto_citar="";
				    			if(v_lang.equals("es")){
				    				texto_citar="<strong>Para citar este artículo:</strong> ";
				    				if(paginas.equals("")){
				    					pag="Avance online";
				    				}
				    				/*formateamos los autores*/
				    				int numAutores = v_autores_notag_inv.split("#").length;
				    				if(numAutores>1 && numAutores<20){
				    					v_autores_notag_inv=v_autores_notag_inv.substring(0, v_autores_notag_inv.lastIndexOf("#")) +"y"+ v_autores_notag_inv.substring(v_autores_notag_inv.lastIndexOf("#")+1);
				    					v_autores_notag_inv=v_autores_notag_inv.replaceAll("#", ", ");
				    					
				    					if(v_autores.indexOf(",")!=-1)
				    						v_autores=v_autores.substring(0, v_autores.lastIndexOf(", ")) +" y "+ v_autores.substring(v_autores.lastIndexOf(", ")+2);
				    				}
				    				if(numAutores>=20){
				    					String autores_fin="";
				    					String[] autores_split = v_autores_notag_inv.split("#");
				    					for(int s=0; s<autores_split.length;s++){
				    						if((s>=0 && s<6)||s==autores_split.length-1){
				    							if(s>0 && s!=autores_split.length-1){
				    								autores_fin=autores_fin+", ";
				    							}
				    							else if(s==autores_split.length-1){
				    								autores_fin=autores_fin+",... ";
				    							}
				    							autores_fin=autores_fin+autores_split[s];
				    						}
				    					}
				    					v_autores_notag_inv=autores_fin;
				    					if(v_autores.indexOf(",")!=-1)
				    						v_autores=v_autores.substring(0, v_autores.lastIndexOf(", ")) +" y "+ v_autores.substring(v_autores.lastIndexOf(", ")+2);
				    				}
				    			}else{
				    				texto_citar="<strong>Cite this article as:</strong> ";
				    				if(paginas.equals("")){
				    					pag="Ahead of print";
				    				}
				    				/*formateamos los autores*/
				    				int numAutores = v_autores_notag_inv.split("#").length;
				    				if(numAutores==2){
				    					v_autores_notag_inv=v_autores_notag_inv.substring(0, v_autores_notag_inv.lastIndexOf("#")) +" & "+ v_autores_notag_inv.substring(v_autores_notag_inv.lastIndexOf("#")+1);
				    					if(v_autores.indexOf(",")!=-1)
				    						v_autores=v_autores.substring(0, v_autores.lastIndexOf(", ")) +" & "+ v_autores.substring(v_autores.lastIndexOf(", ")+2);
				    				}
				    				if(numAutores>2 && numAutores<20){
				    					v_autores_notag_inv=v_autores_notag_inv.substring(0, v_autores_notag_inv.lastIndexOf("#")) +", & "+ v_autores_notag_inv.substring(v_autores_notag_inv.lastIndexOf("#")+1);
				    					v_autores_notag_inv=v_autores_notag_inv.replaceAll("#", ", ");
				    					if(v_autores.indexOf(",")!=-1)
				    						v_autores=v_autores.substring(0, v_autores.lastIndexOf(", ")) +", & "+ v_autores.substring(v_autores.lastIndexOf(", ")+2);
				    				}
				    				if(numAutores>=20){
				    					String autores_fin="";
				    					String[] autores_split = v_autores_notag_inv.split("#");
				    					for(int s=0; s<autores_split.length;s++){
				    						if((s>=0 && s<6)||s==autores_split.length-1){
				    							if(s>0 && s!=autores_split.length-1){
				    								autores_fin=autores_fin+", ";
				    							}
				    							else if(s==autores_split.length-1){
				    								autores_fin=autores_fin+",... ";
				    							}
				    							autores_fin=autores_fin+autores_split[s];
				    						}
				    					}
				    					v_autores_notag_inv=autores_fin;
				    				}
				    			}
				    			
				    			if(nomrevista.containsKey(v_issn)){
				    				v_journalTitle=nomrevista.get(v_issn);
				    			}
				    			if(v_copyright_year==null ||v_copyright_year.equals("")) {
				    				v_copyright_year=v_pubdate.substring(0, 4);
				    			}
				    			v_autores_notag_inv=v_autores_notag_inv.replaceAll(" ,", ",");
				    			String v_citar="<p>"+texto_citar+ v_autores_notag_inv +" ("+v_copyright_year+"). "+v_title+". <em>"+v_journalTitle+(v_volume.length()>0?(", "+v_volume+"</em>"+((v_issue.length()>0 && !v_issue.equals("x"))?("("+v_issue+")"):"")):" ")+"</em>, "+pag+". https://doi.org/"+v_doi_art1+"</p>";
				    			if(v_issn.equals("1889-1861")) {
				    				/*si es la ejpalc cogemos el titulo en ingles*/
				    				v_citar="<p>"+texto_citar+ v_autores_notag_inv +" ("+v_copyright_year+"). "+v_trans_title+". <em>"+v_journalTitle+(v_volume.length()>0?(", "+v_volume+"</em>"+((v_issue.length()>0 && !v_issue.equals("x"))?("("+v_issue+")"):"")):" ")+"</em>, "+pag+". https://doi.org/"+v_doi_art1+"</p>";
				    			}
				    			
				    			if(v_doi_art1.equalsIgnoreCase("")){
				    				v_citar="";
				    			}
				    			//cambio en las normas de APA que las palabras clave no terminan en punto.
				    			/*v_palabras_clave=(v_palabras_clave.equalsIgnoreCase("")?v_palabras_clave:v_palabras_clave+".");
				    			v_keywords=(v_keywords.equalsIgnoreCase("")?v_keywords:v_keywords+".");
				    			v_palabras_chave=(v_palabras_chave.equalsIgnoreCase("")?v_palabras_chave:v_palabras_chave+".");*/
				    			
				    			/*miramos si existen el volumen y el número*/
				    			if(v_volume!=null && !v_volume.equalsIgnoreCase("") && (v_issue==null || v_issue.equalsIgnoreCase(""))) {
				    				v_issue="x";
				    			}
				    			if(v_issue!=null && !v_issue.equalsIgnoreCase("") && v_volume!=null && !v_volume.equalsIgnoreCase(""))
				    			{
				    				System.out.println(v_volume);
				    				System.out.println(v_issue);
				    				query="select id_numero from "+rutasTablas.get(v_issn)+"numero join "+rutasTablas.get(v_issn)+"volumen using (id_volumen) join revista using (id_revista) where volumen="+v_volume+" and numero='"+v_issue+"' and issn='"+v_issn+"'";

				    				pstm = conn.prepareStatement(query);
					    			rs=pstm.executeQuery();
					    			if(rs.next()){
					    				id_numero = rs.getString(1);
					    				System.out.println(id_numero);
					    				rs.close();
						    			pstm.close();
					    			}else{
					    				/*no está el número, así que miramos si ya existe el volumen*/
					    				rs.close();
						    			pstm.close();
						    				query="select id_volumen from "+rutasTablas.get(v_issn)+"volumen join revista using (id_revista) where volumen="+v_volume+" and issn='"+v_issn+"'";
						    				pstm = conn.prepareStatement(query);
						    				rs=pstm.executeQuery();
						    				if(rs.next()){
						    					id_volumen = rs.getString(1);
						    					System.out.println(id_volumen);
						    					rs.close();
						    					pstm.close();
						    				}else{
							    				/*no existe ni el volumen ni el número, así que tenemos que crearlo*/
							    				rs.close();
								    			pstm.close();
							    				query="insert into "+rutasTablas.get(v_issn)+"volumen (id_revista, ano, volumen) values ("+id_revista+","+v_copyright_year+","+v_volume+")";
							    				pstm = conn.prepareStatement(query);
							    				int sid_volumen = pstm.executeUpdate(query, PreparedStatement.RETURN_GENERATED_KEYS);
								    			
								    			ResultSet rs1 = pstm.getGeneratedKeys();

						    				    if (rs1.next()) {
						    				    	sid_volumen = rs1.getInt(1);
						    				    	id_volumen=String.valueOf(sid_volumen);
						    				    }
							    			
						    				    area.setText(area.getText()+"\n\n"+"Creado el volumen");
						    				}
					    				query="insert into "+rutasTablas.get(v_issn)+"numero (id_volumen, numero, paginas, fecha_publicacion, imagen, descripcion) values ("+id_volumen+",'"+v_issue+"','',sysdate(),'','')";
						    			pstm = conn.prepareStatement(query);
						    			int sid_numero = pstm.executeUpdate(query, PreparedStatement.RETURN_GENERATED_KEYS);
						    			
						    			ResultSet rs1 = pstm.getGeneratedKeys();

				    				    if (rs1.next()) {
				    				    	sid_numero = rs1.getInt(1);
				    				    	id_numero=String.valueOf(sid_numero);
				    				    }
						    			rs.close();
						    			pstm.close();
						    			area.setText(area.getText()+"\n\n"+"Creado el número");
					    			}
				    			}else{
				    				/*no hemos recuperado numero de jats con lo que es un avance online->id_numero=0*/
				    				id_numero="0";
				    			}
				    			
				    			
				    			query =  "select id_articulo from "+rutasTablas.get(v_issn)+"articulo where doi like '%"+v_doi_art1+"'";
				    			pstm = conn.prepareStatement(query);
				    			rs=pstm.executeQuery();
				    			/*primero buscamos si el articulo ya está dado de alta en la bd*/
				    			if(!v_doi_art1.equalsIgnoreCase("") && rs.next()){
				    				id_articulo = rs.getString(1);
				    				rs.close();
					    			pstm.close();
					    			
					    				query="update "+rutasTablas.get(v_issn)+"articulo set "
					    					+ ((!checkTitulo.isSelected())?("titulo='"+v_title+"', "):(""))
					    					+ ((!checkTituloEn.isSelected())?("titulo_en='"+v_trans_title+"', " + literal_aux):(""))
					    					+ ((!checkAutores.isSelected())?("autores='<p>"+v_autores+"</p>', "):(""))
					    					+ ((!checkAfiliacion.isSelected())?("afiliacion='<p>"+v_afiliaciones.replaceAll(";;", ";")+"</p>', "):(""))
					    					+ ((!checkResumen.isSelected())?("resumen='"+v_resumen+"', "):(""))
					    					+ ((!checkAbstract.isSelected())?("abstract='"+v_resumen_en+"', "):(""))
					    					+ ((!checkPalabrasClave.isSelected())?("palabras_clave='"+v_palabras_clave+"', "):(""))
					    					+ ((!checkKeywords.isSelected())?("keywords='"+v_keywords+"', "):(""))
					    					+ "contenido='"+contenidoHTML.replaceAll("'", "&#39;")+"', "
					    					+ ((!checkCorrespondencia.isSelected())?("correspondencia='"+v_citar+reemplazar(v_correspondencias_nombres)+"', "):(""))
					    					+ ((!checkPaginas.isSelected())?("paginas='"+paginas+"', "):(""))
					    					+ "archivo='"+nomb_fic_salida_pdf+"', "
					    					+ (v_receiveddate.equals("")?("recibido=null, "):("recibido='"+v_receiveddate+"', "))
					    					+ (v_accepteddate.equals("")?("aceptado=null, "):("aceptado='"+v_accepteddate+"', "))
					    					+ (v_pubdate.equals("")?("publicado=sysdate(), "):("publicado='"+v_pubdate+"', "))
					    					+ "orden="+orden+", "
					    					+ "id_numero="+id_numero+", "
					    					+ "jats='"+nombre_original+"', "
					    					+ "epub='"+fic_salida_epub+"', "
					    					+ "idioma='"+v_lang+"', "
					    					+ "bio='"+v_autores_bio+"', "
					    					+ "extended_abstract='"+v_contentExtended+"' "
					    					+ "where id_articulo="+id_articulo;
					    			}
					    			System.out.println(query);
								    
								    try
									{
										Writer fstream = null;
										fstream = new OutputStreamWriter(new FileOutputStream(v_ruta + "log.txt"), StandardCharsets.UTF_8);
										
										fstream.write(query);
										fstream.close();
										
									}
									catch (IOException exc)
									{
										exc.printStackTrace();
									}
								    //!checkCorrespondencia.isSelected();
									/* comentamos estas lineas para pruebas*/
									
								    if(!checkNoActualizar.isSelected()) {
								    	pstm = conn.prepareStatement(query);
								    	pstm.executeUpdate(query);
					    			
								    	rs.close();
								    	pstm.close();
					    			
								    	area.setText(area.getText()+"\n\n"+"actualizado el artículo");
								    	area.setText(area.getText()+"\n\n"+"Si ha habido cambios en las imágenes o en el XML debes subir los ficheros por FTP y actualizar los datos de META HEAD!");
								    }else {
								    	area.setText(area.getText()+"\n\n"+"No se ha actualizado el HTML");
								    }
					    			botonActualizaHead.setVisible(true);
					    			botonSubefic.setVisible(true);
					    			botonChequea.setVisible(true);
					    			botonGenDOI.setVisible(true);
					    			botonGenDOAJ.setVisible(true);
					    			
				    			}else{
				    				/*no está, así que miramos si ya existe el número*/
				    				rs.close();
					    			pstm.close();
					    			/*ahora creamos el articulo*/
					    			//String v_http_doi=(v_fpage!=null && v_fpage.equals(""))?v_fpage+" - "+v_lpage:"";
					    			
					    				String literal_aux="";
					    				String literal_aux2="";
					    				//si es deporte asignamos la seccion si fuese necesario
					    				
					    				
					    				query="insert into "+rutasTablas.get(v_issn)+"articulo (id_numero, id_revista, titulo, titulo_en, titulo_pt, subtitulo, autores, afiliacion, resumen, abstract, resumo, "
					    					+ "palabras_clave, keywords, palabras_chave, contenido, referencias, correspondencia, metatitle, metadescription, paginas, "
					    					+ "doi, doaj, archivo, recibido, aceptado, publicado, orden, destacado, oculta, epub, jats, idioma, bio, extended_abstract"+literal_aux+") values ("
					    					+ id_numero+","+id_revista+",'"+v_title+"','"+v_trans_title+"','"+v_trans_title2+"','','<p>"+v_autores+"</p>','<p>"+v_afiliaciones+"</p>','"+v_resumen+"','"+v_resumen_en+"','"+v_resumen_pt+"','"
					    					+ v_palabras_clave +"','"+v_keywords+"','"+v_palabras_chave+"','"+contenidoHTML.replaceAll("'", "&#39;")+"','','"+v_citar+reemplazar(v_correspondencias_nombres)+"','','','"+paginas+"','"
					    					+ v_doi_art1+"','','"+nomb_fic_salida_pdf+"',"
					    					+(v_receiveddate.equalsIgnoreCase("")?"null,":("'"+v_receiveddate+"',"))
						    				+ (v_accepteddate.equalsIgnoreCase("")?"null,":("'"+v_accepteddate+"',"))
						    				+ "'"+v_pubdate+"',"
					    					+orden+",0,0,'"+fic_salida_epub+"','"+nombre_original+"','"+v_lang+"','"+v_autores_bio+"','"+v_contentExtended+"'"+literal_aux2+")";
					    			
					    			System.out.println(query);
									try
									{
										Writer fstream = null;
										fstream = new OutputStreamWriter(new FileOutputStream(v_ruta + "log.txt"), StandardCharsets.UTF_8);
										
										fstream.write(query);
										fstream.close();
										
									}
									catch (IOException exc)
									{
										exc.printStackTrace();
									}
									/**/
					    			pstm = conn.prepareStatement(query);
					    			int sid_articulo = pstm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
					    			ResultSet rs1 = pstm.getGeneratedKeys();

			    				    if (rs1.next()) {
			    				    	sid_articulo = rs1.getInt(1);
			    				    	id_articulo=String.valueOf(sid_articulo);
			    				    }
					    			
					    			rs.close();
					    			pstm.close();
					    			
					    			area.setText(area.getText()+"\n\n"+"Creado el artículo");
					    			area.setText(area.getText()+"\n\n"+"Debes subir los ficheros por FTP y actualizar los datos de META HEAD!");
					    			botonActualizaHead.setVisible(true);
					    			botonSubefic.setVisible(true);
					    			botonChequea.setVisible(true);
					    			botonGenDOI.setVisible(true);
					    			botonGenDOAJ.setVisible(true);
				    			}
				    			rs.close();
				    			pstm.close();
				    			conn.close();
				    		}catch (Exception ex){
				    			ex.printStackTrace();
				    			area.setText(area.getText()+"\n***ERROR!!***"+ex.getMessage());
				    			/*try {
									throw new MiExcepcion(ficheroLog, "error al actualizar los datos"+ex.getMessage());
								} catch (MiExcepcion e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}*/
				    		}
				    	}
				    	if(contador>10){

				    	}
				    }
				});
				timer.setRepeats(true);
				timer.start();
			    	
			} catch (Exception exc)
			{
				exc.printStackTrace();
				area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
				try {
					throw new MiExcepcion(ficheroLog, "***ERROR!!***"+exc.getMessage());
				} catch (MiExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    	}
	}
    
    private static void generamosMetadatos(String fic_salida_pdf, String fichero_original, String subject, String keywords, ArrayList <String[]> ficherosSubir, ArrayList <String[]> dois_referencias, File ficheroLog) throws MiExcepcion
    {
    	//fic_salida_pdf=fic_salida_pdf.replaceAll("\\-", "_");
    	while(v_autores_notag.indexOf("&")!=-1) {
    		String part1=v_autores_notag.substring(0, v_autores_notag.indexOf("&"));
    		String part2=v_autores_notag.substring(v_autores_notag.indexOf("&"));
    		String part_replace=part2.substring(0,part2.indexOf(";")+1);
    		String part3=part2.substring(part2.indexOf(";")+1);
    		part_replace=devuelveCharFromHTML(part_replace);
    		v_autores_notag=part1+part_replace+part3;
    		//System.out.println("autores...." +v_autores_notag);
    	}
    	geneMeta (null, fichero_original.substring(0, fichero_original.indexOf(".xml"))+".pdf", v_title, v_autores_notag, subject, keywords,fic_salida_pdf, v_copyright_year);
		String ficheropdf[] = {"archivo",fic_salida_pdf};
		ficherosSubir.add(ficheropdf);
		
		for (int i=0;i<ficherosSubir.size();i++)
		{
			File fic_aux= new File(ficherosSubir.get(i)[1]);
			String dic = ficherosSubir.get(i)[0];
			String nuevo_dir_txt = v_ruta;
			if(!dic.equals("")){
				File nuevo_dir = new File(v_ruta+"\\"+dic);
				nuevo_dir_txt=v_ruta+"\\"+dic;
				if (!nuevo_dir.exists()){
					nuevo_dir.mkdir();
				}
			}
			try{
				Files.copy(Paths.get(ficherosSubir.get(i)[1]), Paths.get(nuevo_dir_txt+"\\"+fic_aux.getName()), StandardCopyOption.REPLACE_EXISTING);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		ArrayList<String> cabecera_excel = new ArrayList<String>();
		cabecera_excel.add("revista/libro");
		cabecera_excel.add("autor");
		cabecera_excel.add("titulo");
		cabecera_excel.add("año");
		cabecera_excel.add("link");
		cabecera_excel.add("email");
		cabecera_excel.add("email");
		cabecera_excel.add("email");
		cabecera_excel.add("email");
		
		ArrayList<ArrayList<String>> contenido_excel = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<dois_referencias.size();i++)
		{
			String doi_ref = dois_referencias.get(i)[4];
			ArrayList<String> file_excel= new ArrayList<String>();
			if(doi_ref!=null && !doi_ref.equals(""))
			{
				if(doi_ref.indexOf("http")==-1)
				{
					doi_ref="https://doi.org/"+doi_ref;
					dois_referencias.get(i)[4]=doi_ref;
				}
				String encontrar = buscarTextoPagina(doi_ref,"@",ficheroLog);
				//System.out.println(encontrar);
				for(int k=0; k<dois_referencias.get(i).length; k++){
					file_excel.add(dois_referencias.get(i)[k].replaceAll(";", ","));
				}
				String[] emails_excel=encontrar.split(";");
				for (int l=0; l<emails_excel.length; l++){
					file_excel.add(emails_excel[l]);
				}
			}else{
				for(int k=0; k<dois_referencias.get(i).length; k++){
					file_excel.add(dois_referencias.get(i)[k].replaceAll(";", ","));
				}
			}
			contenido_excel.add(file_excel);
			
		}
		escribirExcel(cabecera_excel, contenido_excel, fichero_referencias);
    }
    
    private static void prepararFichero(String fichero_original, String nombre_original, ArrayList <String[]> ficherosSubir)
    {
    	String auxPath=fichero_original;
		String fichero_htm = nombre_original.substring(0, nombre_original.indexOf(".xml"))+".htm";
		String fichero_temporal = nombre_original.substring(0, nombre_original.lastIndexOf('.')) + "_temp" + nombre_original.substring(nombre_original.lastIndexOf('.'));
		String ficherohtml[] = {"",fichero_htm};
		ficherosSubir.add(ficherohtml);
		
		/*File fichero_aux = new File(nombre_original);
		File directorio = fichero_aux.getParentFile();
		File directorio_aux = new File(directorio+"\\subir");
		/*if(directorio_aux.exists()){
			directorio_aux.delete();
		}
		directorio_aux.mkdir();*/
		File directorio_original = new File(fichero_original).getParentFile();
		//leemos los ficheros del directorio que son imagenes
		if(directorio_original!=null && directorio_original.isDirectory())
		{
			for (final File ficheroEntrada : directorio_original.listFiles()) {
		        if (!ficheroEntrada.isDirectory()) {
		        	if(ficheroEntrada.getName().toLowerCase().endsWith(".jpg") ||
		        		ficheroEntrada.getName().toLowerCase().endsWith(".jpeg") ||
		        		ficheroEntrada.getName().toLowerCase().endsWith(".gif") ||
		        		ficheroEntrada.getName().toLowerCase().endsWith(".bmp") ||
		        		ficheroEntrada.getName().toLowerCase().endsWith(".png") || 
		        		ficheroEntrada.getName().toLowerCase().endsWith(".tif") ||
		        		ficheroEntrada.getName().toLowerCase().endsWith(".tiff")){
		        		String ficheroimg[] = {"jats_files",ficheroEntrada.getAbsolutePath()};
						ficherosSubir.add(ficheroimg);
		        	}
		        	if(ficheroEntrada.getName().toLowerCase().endsWith(".epub")){
		        		String ficheroepub[] = {"archivo",ficheroEntrada.getAbsolutePath()};
						ficherosSubir.add(ficheroepub);
		        	}
		        } else {
		            System.out.println(ficheroEntrada.getName());
		        }
		    }
		}
	    
	    
	    // Leemos todo el fichero para quitar el elemento DOCTYPE
	    String auxFileReader = "";
	    BufferedReader br = null;
		FileReader fr = null;
		try
		{
			//fr = new FileReader(auxPath);
			//br = new BufferedReader(fr);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(auxPath), "UTF8"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
            {
                   if( sCurrentLine.indexOf("<!DOCTYPE article PUBLIC")<0 )
                   {
                	   	sCurrentLine=sCurrentLine.replaceAll("\\&#160;", " ");
                	   	sCurrentLine=sCurrentLine.replaceAll("\\&#9;", "");
                	   	sCurrentLine=sCurrentLine.replaceAll("\\#", "xxx");
                	   	auxFileReader += sCurrentLine;
                   }
                   else
                   {
                		//InputStreamReader isr = new InputStreamReader(new FileInputStream("caracteres.txt"), "UTF-8");
                		StringBuffer cadena = new StringBuffer();
                		FileReader fr_chars = new FileReader("caracteres.txt");
                		BufferedReader bf_chars = new BufferedReader(fr_chars);
                		String sCadena="";
                		while ((sCadena = bf_chars.readLine())!=null) 
                		{
                			sCadena.indexOf((char)181);
                			//String UTF8Str = new String(sCadena.getBytes(),"UTF-8");
                			Charset.forName("UTF-8").decode(ByteBuffer.wrap(sCadena.getBytes("UTF-8")));
                			sCadena = Charset.forName("UTF-8").decode(ByteBuffer.wrap(sCadena.getBytes())).toString();
                			cadena.append(sCadena);
                		} 
                		bf_chars.close();
                         auxFileReader += sCurrentLine.substring(0,sCurrentLine.indexOf("<!DOCTYPE article PUBLIC"))+ "<!DOCTYPE article [ "+ cadena.toString() + "] >";
                                 /*"<!DOCTYPE article [ <!ENTITY Aacute \"&#193;\" > <!ENTITY aacute \"&#225;\" > <!ENTITY Acirc \"&#194;\" > <!ENTITY acirc \"&#226;\" > <!ENTITY acute \"&#180;\" > <!ENTITY AElig \"&#198;\" > <!ENTITY aelig \"&#230;\" > <!ENTITY Agrave \"&#192;\" > <!ENTITY agrave \"&#224;\" > <!ENTITY alefsym \"&#8501;\" > <!ENTITY Alpha \"&#913;\" > <!ENTITY alpha \"&#945;\" > <!ENTITY amp \"&#38;\" > <!ENTITY and \"&#8743;\" > "+
                                 "<!ENTITY ang \"&#8736;\" > <!ENTITY Aring \"&#197;\" > <!ENTITY aring \"&#229;\" > <!ENTITY asymp \"&#8776;\" > <!ENTITY Atilde \"&#195;\" > <!ENTITY atilde \"&#227;\" > <!ENTITY Auml \"&#196;\" > <!ENTITY auml \"&#228;\" > <!ENTITY bdquo \"&#8222;\" > <!ENTITY Beta \"&#914;\" > <!ENTITY beta \"&#946;\" > <!ENTITY brvbar \"&#166;\" > <!ENTITY bull \"&#8226;\" > <!ENTITY cap \"&#8745;\" > <!ENTITY Ccedil \"&#199;\" > "+
                                 "<!ENTITY ccedil \"&#231;\" > <!ENTITY cedil \"&#184;\" > <!ENTITY cent \"&#162;\" > <!ENTITY Chi \"&#935;\" > <!ENTITY chi \"&#967;\" > <!ENTITY circ \"&#710;\" > <!ENTITY clubs \"&#9827;\" > <!ENTITY cong \"&#8773;\" > <!ENTITY copy \"&#169;\" > <!ENTITY crarr \"&#8629;\" > <!ENTITY cup \"&#8746;\" > <!ENTITY curren \"&#164;\" > <!ENTITY dagger \"&#8224;\" > <!ENTITY Dagger \"&#8225;\" > <!ENTITY darr \"&#8595;\" > "+
                                 "<!ENTITY dArr \"&#8659;\" > <!ENTITY deg \"&#176;\" > <!ENTITY Delta \"&#916;\" > <!ENTITY delta \"&#948;\" > <!ENTITY diams \"&#9830;\" > <!ENTITY divide \"&#247;\" > <!ENTITY Eacute \"&#201;\" > <!ENTITY eacute \"&#233;\" > <!ENTITY Ecirc \"&#202;\" > <!ENTITY ecirc \"&#234;\" > <!ENTITY Egrave \"&#200;\" > <!ENTITY egrave \"&#232;\" > <!ENTITY empty \"&#8709;\" > <!ENTITY emsp \"&#8195;\" > <!ENTITY ensp \"&#8194;\" >"+
                                 " <!ENTITY Epsilon \"&#917;\" > <!ENTITY epsilon \"&#949;\" > <!ENTITY equiv \"&#8801;\" > <!ENTITY Eta \"&#919;\" > <!ENTITY eta \"&#951;\" > <!ENTITY ETH \"&#208;\" > <!ENTITY eth \"&#240;\" > <!ENTITY Euml \"&#203;\" > <!ENTITY euml \"&#235;\" > <!ENTITY euro \"&#8364;\" > <!ENTITY exist \"&#8707;\" > <!ENTITY forall \"&#8704;\" > <!ENTITY frac12 \"&#189;\" > <!ENTITY frac14 \"&#188;\" > <!ENTITY frac34 \"&#190;\" > "+
                                 "<!ENTITY frasl \"&#8260;\" > <!ENTITY Gamma \"&#915;\" > <!ENTITY gamma \"&#947;\" > <!ENTITY ge \"&#8805;\" > <!ENTITY gt \"&#62;\" > <!ENTITY harr \"&#8596;\" > <!ENTITY hArr \"&#8660;\" > <!ENTITY hearts \"&#9829;\" > <!ENTITY hellip \"&#8230;\" > <!ENTITY Iacute \"&#205;\" > <!ENTITY iacute \"&#237;\" > <!ENTITY Icirc \"&#206;\" > <!ENTITY icirc \"&#238;\" > <!ENTITY iexcl \"&#161;\" > <!ENTITY Igrave \"&#204;\" > "+
                                 "<!ENTITY igrave \"&#236;\" > <!ENTITY image \"&#8465;\" > <!ENTITY infin \"&#8734;\" > <!ENTITY int \"&#8747;\" > <!ENTITY Iota \"&#921;\" > <!ENTITY iota \"&#953;\" > <!ENTITY iquest \"&#191;\" > <!ENTITY isin \"&#8712;\" > <!ENTITY Iuml \"&#207;\" > <!ENTITY iuml \"&#239;\" > <!ENTITY Kappa \"&#922;\" > <!ENTITY kappa \"&#954;\" > <!ENTITY Lambda \"&#923;\" > <!ENTITY lambda \"&#955;\" > <!ENTITY lang \"&#9001;\" > "+
                                 "<!ENTITY laquo \"&#171;\" > <!ENTITY larr \"&#8592;\" > <!ENTITY lArr \"&#8656;\" > <!ENTITY lceil \"&#8968;\" > <!ENTITY ldquo \"&#8220;\" > <!ENTITY le \"&#8804;\" > <!ENTITY lfloor \"&#8970;\" > <!ENTITY lowast \"&#8727;\" > <!ENTITY loz \"&#9674;\" > <!ENTITY lrm \"&#8206;\" > <!ENTITY lsaquo \"&#8249;\" > <!ENTITY lsquo \"&#8216;\" > <!ENTITY lt \"&#60;\" > <!ENTITY macr \"&#175;\" > <!ENTITY mdash \"&#8212;\" > "+
                                 "<!ENTITY micro \"&#181;\" > <!ENTITY middot \"&#183;\" > <!ENTITY minus \"&#8722;\" > <!ENTITY Mu \"&#924;\" > <!ENTITY mu \"&#956;\" > <!ENTITY nabla \"&#8711;\" > <!ENTITY nbsp \"&#160;\" > <!ENTITY ndash \"&#8211;\" > <!ENTITY ne \"&#8800;\" > <!ENTITY ni \"&#8715;\" > <!ENTITY not \"&#172;\" > <!ENTITY notin \"&#8713;\" > <!ENTITY nsub \"&#8836;\" > <!ENTITY Ntilde \"&#209;\" > <!ENTITY ntilde \"&#241;\" > "+
                                 "<!ENTITY Nu \"&#925;\" > <!ENTITY nu \"&#957;\" > <!ENTITY Oacute \"&#211;\" > <!ENTITY oacute \"&#243;\" > <!ENTITY Ocirc \"&#212;\" > <!ENTITY ocirc \"&#244;\" > <!ENTITY OElig \"&#338;\" > <!ENTITY oelig \"&#339;\" > <!ENTITY Ograve \"&#210;\" > <!ENTITY ograve \"&#242;\" > <!ENTITY oline \"&#8254;\" > <!ENTITY Omega \"&#937;\" > <!ENTITY omega \"&#969;\" > <!ENTITY Omicron \"&#927;\" > <!ENTITY omicron \"&#959;\" >"+
                                 " <!ENTITY oplus \"&#8853;\" > <!ENTITY or \"&#8744;\" > <!ENTITY ordf \"&#170;\" > <!ENTITY ordm \"&#186;\" > <!ENTITY Oslash \"&#216;\" > <!ENTITY oslash \"&#248;\" > <!ENTITY Otilde \"&#213;\" > <!ENTITY otilde \"&#245;\" > <!ENTITY otimes \"&#8855;\" > <!ENTITY Ouml \"&#214;\" > <!ENTITY ouml \"&#246;\" > <!ENTITY para \"&#182;\" > <!ENTITY part \"&#8706;\" > <!ENTITY permil \"&#8240;\" > <!ENTITY perp \"&#8869;\" > "+
                                 "<!ENTITY Phi \"&#934;\" > <!ENTITY phi \"&#966;\" > <!ENTITY Pi \"&#928;\" > <!ENTITY pi \"&#960;\" > <!ENTITY piv \"&#982;\" > <!ENTITY plusmn \"&#177;\" > <!ENTITY pound \"&#163;\" > <!ENTITY prime \"&#8242;\" > <!ENTITY Prime \"&#8243;\" > <!ENTITY prod \"&#8719;\" > <!ENTITY prop \"&#8733;\" > <!ENTITY Psi \"&#936;\" > <!ENTITY psi \"&#968;\" > <!ENTITY quot \"&#34;\" > <!ENTITY radic \"&#8730;\" > <!ENTITY rang \"&#9002;\" >"+
                                 " <!ENTITY raquo \"&#187;\" > <!ENTITY rarr \"&#8594;\" > <!ENTITY rArr \"&#8658;\" > <!ENTITY rceil \"&#8969;\" > <!ENTITY rdquo \"&#8221;\" > <!ENTITY real \"&#8476;\" > <!ENTITY reg \"&#174;\" > <!ENTITY rfloor \"&#8971;\" > <!ENTITY Rho \"&#929;\" > <!ENTITY rho \"&#961;\" > <!ENTITY rlm \"&#8207;\" > <!ENTITY rsaquo \"&#8250;\" > <!ENTITY rsquo \"&#8217;\" > <!ENTITY sbquo \"&#8218;\" > <!ENTITY Scaron \"&#352;\" > "+
                                 "<!ENTITY scaron \"&#353;\" > <!ENTITY sdot \"&#8901;\" > <!ENTITY sect \"&#167;\" > <!ENTITY shy \"&#173;\" > <!ENTITY Sigma \"&#931;\" > <!ENTITY sigma \"&#963;\" > <!ENTITY sigmaf \"&#962;\" > <!ENTITY sim \"&#8764;\" > <!ENTITY spades \"&#9824;\" > <!ENTITY sub \"&#8834;\" > <!ENTITY sube \"&#8838;\" > <!ENTITY sum \"&#8721;\" > <!ENTITY sup \"&#8835;\" > <!ENTITY sup1 \"&#185;\" > <!ENTITY sup2 \"&#178;\" > "+
                                 "<!ENTITY sup3 \"&#179;\" > <!ENTITY supe \"&#8839;\" > <!ENTITY szlig \"&#223;\" > <!ENTITY Tau \"&#932;\" > <!ENTITY tau \"&#964;\" > <!ENTITY there4 \"&#8756;\" > <!ENTITY Theta \"&#920;\" > <!ENTITY theta \"&#952;\" > <!ENTITY thetasym \"&#977;\" > <!ENTITY thinsp \"&#8201;\" > <!ENTITY THORN \"&#222;\" > <!ENTITY thorn \"&#254;\" > <!ENTITY tilde \"&#732;\" > <!ENTITY times \"&#215;\" > <!ENTITY trade \"&#8482;\" >"+
                                 " <!ENTITY Uacute \"&#218;\" > <!ENTITY uacute \"&#250;\" > <!ENTITY uarr \"&#8593;\" > <!ENTITY uArr \"&#8657;\" > <!ENTITY Ucirc \"&#219;\" > <!ENTITY ucirc \"&#251;\" > <!ENTITY Ugrave \"&#217;\" > <!ENTITY ugrave \"&#249;\" > <!ENTITY uml \"&#168;\" > <!ENTITY upsih \"&#978;\" > <!ENTITY Upsilon \"&#933;\" > <!ENTITY upsilon \"&#965;\" > <!ENTITY Uuml \"&#220;\" > <!ENTITY uuml \"&#252;\" > <!ENTITY weierp \"&#8472;\" >"+
                                 " <!ENTITY cacute \"&#263;\" > <!ENTITY xxxx201C \"&#8220;\" > <!ENTITY xxxx201D \"&#8221;\" > <!ENTITY xxxx2019 \"&#8217;\" > <!ENTITY xxxx2018 \"&#8216;\" > <!ENTITY xxxx2014 \"&#8212;\" > <!ENTITY xxxx2015 \"&#8212;\" > <!ENTITY xxxx2013 \"&#8211;\" > <!ENTITY xxxx3c3 \"&#963;\">  <!ENTITY xxxx2026 \"&#8230;\"> <!ENTITY xxxxBA \"&#186;\"> <!ENTITY xxxx3b1 \"&#945;\"> <!ENTITY xxxx2264 \"&#8804;\"> <!ENTITY xxxx3bb \"&#955;\"> <!ENTITY xxxxab \"&#171;\"> <!ENTITY xxxxbb \"&#187;\"> <!ENTITY xxxx3b7 \"&#951;\">" + 
                                 "<!ENTITY xxxx0107 \"&#263;\" > <!ENTITY xxxx17D \"&#381;\" >   <!ENTITY Cacute \"&#262;\" > <!ENTITY eng \"&#331;\" > <!ENTITY Sacute \"&#346;\" > <!ENTITY abreve \"&#259;\" > <!ENTITY Xi \"&#926;\" > <!ENTITY xi \"&#958;\" > <!ENTITY Yacute \"&#221;\" > <!ENTITY yacute \"&#253;\" > <!ENTITY yen \"&#165;\" > <!ENTITY Yuml \"&#376;\" > <!ENTITY yuml \"&#255;\" > <!ENTITY Zeta \"&#918;\" > <!ENTITY zeta \"&#950;\" > <!ENTITY zwj \"&#8205;\" > <!ENTITY zwnj \"&#8204;\" >  ] >";*/
                   }
            }
		}
		catch (IOException exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			try
			{
				if (br != null) br.close();
				if (fr != null) fr.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		
		// Escribimos el fichero
		BufferedWriter bw = null;
		FileWriter fw = null;
		try
		{
			Writer fstream = null;
			fstream = new OutputStreamWriter(new FileOutputStream(fichero_temporal), StandardCharsets.UTF_8);
			
			fstream.write(auxFileReader);
			fstream.close();
			
		}
		catch (IOException exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw != null) bw.close();
				if (fw != null) fw.close();

			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
    }
    
    private static Document procesarFicheroTemporal(String fichero_temporal, String fichero_htm)
    {
    	try{
	    	InputStream inputStream= new FileInputStream(fichero_temporal);
	        Reader reader = new InputStreamReader(inputStream,"UTF-8");
	        InputSource is = new InputSource(reader);
	        is.setEncoding("UTF-8");
	
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(is);				
	
			doc.getDocumentElement().normalize();
			//System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("article");
			
			String html = "";
			
			if( nList.getLength()==0 )
			{
				System.out.println("*** WARNING!!! No hay nodo ARTICLE");
			}
			else if ( nList.getLength()>1 )
			{
				System.out.println("*** WARNING!!! Hay más de un nodo ARTICLE");
			}
			else
			{
				for (int temp = 0; temp < nList.getLength(); temp++)
				{
					Node nNode = nList.item(temp);
					html += procesar(nNode);
				}
			}
			strIndexSec = "<div class=\"indexSec\" id=\"indexSec\">" + strIndexSec + "</div>";
			strIndexFig = "<div class=\"indexFig\" id=\"indexFig\">" + strIndexFig + "</div>";
			strIndexTable = "<div class=\"indexTable\" id=\"indexTable\">" + strIndexTable + "</div>";
			strIndexEquation = "<div class=\"indexEquation\" id=\"indexEquation\">" + strIndexEquation + "</div>";
			strIndexRef = "<div class=\"indexRef\" id=\"indexRef\">" + strIndexRef + "</div>";
			
			/*String auxCab = "<div class=\"jats_cabecera\">";
			if( !publisherName.equalsIgnoreCase("") )
				auxCab += publisherName + "<br>";
			
			if( !journalTitle.equalsIgnoreCase("") )
				auxCab += journalTitle;
			
			if( !issn.equalsIgnoreCase("") )
				auxCab += " - " + issn;
			
			if( !issn2.equalsIgnoreCase("") )
				auxCab += issn2;
			
			auxCab += "<br>";
			
			if( !volume.equalsIgnoreCase("") )
				auxCab += volume;
			if( !issue.equalsIgnoreCase("") )
				auxCab += issue;
			
			auxCab += " (";
			
			if( !fPage.equalsIgnoreCase("") )
				auxCab += fPage;
			if( !lPage.equalsIgnoreCase("") )
				auxCab += lPage;
			
			auxCab += ")";
	
	
			auxCab += "</div>";*/
			
			html = "<link href=\"https://journals.copmadrid.org/mathml.css?v=1.1\" rel=\"stylesheet\" type=\"text/css\"> <link href=\"https://journals.copmadrid.org/jats_new.css?v=1.1\" rel=\"stylesheet\" type=\"text/css\"><table class=\"jats_table_frame\"><tr><td valign=\"top\">" + html + "</td><td class=\"jats_links_td\" valign=\"top\">"+ strIndexSec + strIndexFig + strIndexTable + strIndexEquation + strIndexRef + "</td></tr></table>";
			html = html.replaceAll("<math display=\"block\">", "<math display=\"block\"><mrow>");
			html = html.replaceAll("</math>", "</mrow></math>");
			html = reemplazar(html);
			
			//System.out.println("*** OUTPUT ARTICLE");
			//System.out.println(html);
			
			
			if( !fichero_htm.isEmpty() )
			{
				// guardamos fichero
				//FileWriter fichero = new FileWriter(fichero_htm);
				
				
				Writer fstream1 = null;
				fstream1 = new OutputStreamWriter(new FileOutputStream(fichero_htm), StandardCharsets.UTF_8);
				
				fstream1.write(html + "\r\n");
				fstream1.close();
				
				//Insertamos el texto creado y si trabajamos
				//en Windows terminaremos cada línea con "\r\n"
				//fichero.write(html + "\r\n");
				//cerramos el fichero
				//fichero.close();
			}
			else
			{
				//System.out.println(html);
			}
			return doc;
    	}catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    private static String[] procesarDocReferencias(Document doc, String fichero_sql, ArrayList <String[]> dois_referencias, ArrayList <String[]> ficherosSubir, String subject, String keywords)
    {
    	String valor1 =procesarAutores(doc);
		String valor2 =procesarReferencias(doc, dois_referencias);
		
		valor1=reemplazar(valor1);
		valor2=reemplazar(valor2);
		valor1=valor1.replaceAll("&percnt;", "%");
		valor2=valor1.replaceAll("&percnt;", "%");
		
		BufferedWriter bw1 = null;
		FileWriter fw1 = null;
		try
		{
			File fic_aux=new File(fichero_sql);
			if(fic_aux.exists()){
				fic_aux.delete();
			}
			Writer fstream = null;
			//BufferedWriter out = null;
			fstream = new OutputStreamWriter(new FileOutputStream(fichero_sql), StandardCharsets.UTF_8);
			
			fstream.write(valor1+System.getProperty("line.separator")+valor2);
			fstream.close();
			
			String ficherosql[] = {"sql",fichero_sql};
			ficherosSubir.add(ficherosql);
			
		}
		catch (IOException exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw1 != null) bw1.close();
				if (fw1 != null) fw1.close();

			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		if(v_copyright_year==null ||v_copyright_year.equals("")) {
			v_copyright_year=v_pubdate.substring(0, 4);
		}
		if(v_volume!=null && !v_volume.equals("")){
			if(v_volume.equals("x"))
				subject=v_journalTitle+", "+v_copyright_year+", "+ v_volume+" "+v_fpage+"-"+v_lpage+ ". doi:" +v_doi_art.replaceAll("https://doi.org/", "");
			else
				subject=v_journalTitle+", "+v_copyright_year+", "+ v_volume+"("+v_issue+") "+v_fpage+"-"+v_lpage+ ". doi:" +v_doi_art.replaceAll("https://doi.org/", "");
		}else
		{
			subject=v_journalTitle+", "+v_copyright_year+" Ahead of print. doi:"+v_doi_art.replaceAll("https://doi.org/", "");
		}
		if(v_lang_sec.equals("en"))
		{
			keywords=v_palabras_clave+", "+v_keywords;
		}else
		{
			keywords=v_keywords+", "+v_palabras_clave;
		}
		if(v_lang_sec2.equals("pt")) {
			keywords=keywords+", "+v_palabras_chave;
		}
		String[] retorno = new String[2];
		retorno[0]=subject;
		retorno[1]=keywords;
		return retorno;
    }

	private static String procesar (Node root)
	{
		// Articulo
		String auxStr = "<div class=\"jats_article\">";;
		int contFront = 0;
		int contBody = 0;
		int contFloatsGroup = 0;
		int contBack = 0;
		
		System.out.println("*** INFO---COMIENZA TRATAMIENTO: " + root.getNodeName());
		NodeList cab = root.getChildNodes();
		//System.out.println("* Hijos:" + cab.getLength());
		NamedNodeMap att = root.getAttributes();
		//System.out.println("* Atrib:" + att.getLength());
		
		// Procesamos atributos
		for (int temp2 = 0; temp2 < att.getLength(); temp2++)
		{
			Node at = att.item(temp2);
			//System.out.println("*** ATR Name:" +  at.getNodeName() + " - Value: " + at.getNodeValue() + " - Type: " + at.getNodeType()); 
		}
		
		// Procesamos nodos
		for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			//System.out.println("*** INFO NodeName: " + nNode.getNodeName() + " - Type: " + nNode.getNodeType() + " - Value: " + nNode.getNodeValue());
			if( nNode.getNodeType()==Node.ELEMENT_NODE )
			{
				if ( nNode.getNodeName().equalsIgnoreCase("article") )
				{
					v_lang = buscarAtributo(nNode,"xml:lang");
					if (v_lang.equals("en"))
						v_lang_sec="es";
					if (v_lang.equals("es"))
						v_lang_sec="en";
				}
				
				if ( nNode.getNodeName().equalsIgnoreCase("front") )
				{
					contFront++;
					if ( contFront==1 )
					{
						auxStr += procesarFront( nNode );
						
					}
					else
					{
						System.out.println("*** WARNING!!! Detectado nodo front " + contFront + " veces");
					}
				}
				else if ( nNode.getNodeName().equalsIgnoreCase("body") )
				{
					contBody++;
					if ( contBody==1 )
					{
						auxStr += procesarBody( nNode );
					}
					else
					{
						System.out.println("*** WARNING!!! Detectado nodo body " + contBody + " veces");
					}
				}
				else if ( nNode.getNodeName().equalsIgnoreCase("floats-group") )
				{
					contFloatsGroup++;
					if ( contFloatsGroup==1 )
					{
						//auxStr += procesarFloatsGroup( nNode );
					}
					else
					{
						System.out.println("*** WARNING!!! Detectado nodo floats-group " + contFloatsGroup + " veces");
					}
				}
				else if ( nNode.getNodeName().equalsIgnoreCase("back") )
				{
					contBack++;
					if ( contBack==1 )
					{
						auxStr += procesarBack( nNode );
					}
					else
					{
						System.out.println("*** WARNING!!! Detectado nodo back " + contBack + " veces");
					}
				}
				else
				{
					System.out.println("*** WARNING!!! nodo desconocido " + nNode.getNodeName() + "[" + nNode.getNodeType() + "]");
				}
			}
		}
		auxStr += "</div>";
		return auxStr;
		
	}
	
	private static String procesarReferencias (Document doc, ArrayList<String[]> referencias)
	{
		String vuelta="";
		NodeList nList_princ1 = doc.getElementsByTagName("article-id");
		String doi_articulo="";
		for (int temp = 0; temp < nList_princ1.getLength(); temp++)
		{
			Node nNode = nList_princ1.item(temp);
			
			if( nNode.getNodeType()==Node.ELEMENT_NODE && buscarAtributo(nNode,"pub-id-type").equals("doi") )
			{
				doi_articulo = nNode.getTextContent();
				break;
			}
		}
		vuelta=vuelta+System.getProperty("line.separator")+("delete from revista_articulo_referencia where id_articulo=(select id_articulo from "+rutasTablas.get(v_issn)+"articulo where doi like '%"+doi_articulo.substring(doi_articulo.lastIndexOf("/")+1)+"');");
		
		NodeList nList_princ = doc.getElementsByTagName("ref");
		for (int temp = 0; temp < nList_princ.getLength(); temp++)
		{
			String referencia="";
			String auxNombre="";
			String auxNomb1="";
			String journal_title="";
			String title="";
			String pages="";
			String volumen="";
			String year="";
			String issue="";
			String doi="";
			String pmid="";
			String publisher_name="";
			String tipo="";
			Node nNode = nList_princ.item(temp);
			NodeList hijos =nNode.getChildNodes();
			for (int temp1 = 0; temp1<hijos.getLength(); temp1++){
				Node nNode_hijo = hijos.item(temp1);
				if(nNode_hijo.getNodeName().equals("element-citation"))
				{
					NodeList citations = nNode_hijo.getChildNodes();
					for (int iter_cit=0; iter_cit<citations.getLength(); iter_cit++)
					{
						Node citation = citations.item(iter_cit);
						if(citation.getNodeName().equalsIgnoreCase("person-group") && buscarAtributo(citation,"person-group-type").equals("author") )
						{
							Node personGroup = citation;
							NodeList names = personGroup.getChildNodes();
							for(int iter_person=0; iter_person<names.getLength(); iter_person++)
							{
								Node person = names.item(iter_person);
								if(person.getNodeName().equals("name")){
									NodeList cab = person.getChildNodes();
									for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
									{
										Node nNode_aux = cab.item(temp3);
										if( nNode_aux.getNodeType()==Node.ELEMENT_NODE && nNode_aux.getNodeName().equalsIgnoreCase("given-names") )
										{
											auxNombre = auxNombre + ", " +nNode_aux.getTextContent();
											break;
										}
									}
									for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
									{
										Node nNode_aux = cab.item(temp3);
										if( nNode_aux.getNodeType()==Node.ELEMENT_NODE && nNode_aux.getNodeName().equalsIgnoreCase("surname") )
										{
											auxNombre = auxNombre + " " +nNode_aux.getTextContent();
											break;
										}
									}
								}
							}
							if (auxNombre.length()>0){
								auxNombre=auxNombre.substring(2, auxNombre.length());
								if(auxNombre.indexOf(",")!=-1) 
									auxNomb1=auxNombre.substring(0, auxNombre.indexOf(","));
								else
									auxNomb1=auxNombre;
							}
						}else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("year") )
						{
							year = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("article-title") )
						{
							title = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("source") )
						{
							journal_title = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("volume") )
						{
							volumen = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("fpage") )
						{
							pages = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("lpage") )
						{
							pages = pages + "-" +citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("pub-id") && buscarAtributo(citation,"pub-id-type").equals("doi"))
						{
							doi = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("pub-id") && buscarAtributo(citation,"pub-id-type").equals("pmid"))
						{
							pmid = citation.getTextContent();
						}
						
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("publisher-name") )
						{
							publisher_name = citation.getTextContent();
						}
						else if(citation.getNodeType()==Node.ELEMENT_NODE && citation.getNodeName().equalsIgnoreCase("issue") )
						{
							issue = citation.getTextContent();
						}
						
					}
					if(buscarAtributo(nNode_hijo,"publication-type").equals("journal")){
						tipo="journal";
						referencia="<meta name=\"citation_reference\" content=\""
								+(journal_title.equals("")?"":"citation_journal_title="+journal_title+";")
								+(auxNombre.equals("")?"":"citation_author="+auxNombre+";")
								+(title.equals("")?"":"citation_title="+title+";")
								+(pages.equals("")?"":"citation_pages="+pages+";")
								+(volumen.equals("")?"":"citation_volume="+volumen+";")
								+(issue.equals("")?"":"citation_issue="+issue+";")
								+(year.equals("")?"":"citation_year="+year+";")
								+(pmid.equals("")?"":"citation_pmid="+pmid+";")
								+(doi.equals("")?"":"citation_doi="+doi+";")
								+(publisher_name.equals("")?"":"citation_publisher="+publisher_name+";")
								
								+"\" >";
					}
					else if(buscarAtributo(nNode_hijo,"publication-type").equals("book")){
						tipo="book";
						referencia="<meta name=\"citation_reference\" content=\""
								+(journal_title.equals("")?"":"citation_book_title="+journal_title+";")
								+(auxNombre.equals("")?"":"citation_author="+auxNombre+";")
								+(title.equals("")?"":"citation_title="+title+";")
								+(year.equals("")?"":"citation_year="+year+";")
								+(publisher_name.equals("")?"":"citation_publisher="+publisher_name+";")
								+"\" >";
					}
					else{
						tipo="other";
						referencia="<meta name=\"citation_reference\" content=\""
								+(journal_title.equals("")?"":"citation_title="+journal_title+";")
								+(auxNombre.equals("")?"":"citation_author="+auxNombre+";")
								+(title.equals("")?"":"citation_title="+title+";")
								+(year.equals("")?"":"citation_year="+year+";")
								+(publisher_name.equals("")?"":"citation_publisher="+publisher_name+";")
								+"\" >";
					}
					String referencia_concreta[]={journal_title,auxNomb1,title,year,doi};
					referencias.add(referencia_concreta);
					String consulta="insert into revista_articulo_referencia (id_articulo, autor, titulo, nombre_fuente, paginas, volumen, ano, numero, doi, pmi, publisher, tipo, linea_completa, orden) values (";
					consulta += "(select id_articulo from "+rutasTablas.get(v_issn)+"articulo where doi like '%"+doi_articulo.substring(doi_articulo.lastIndexOf("/")+1)+"'),";
					consulta += "'"+auxNombre.replaceAll("'", "´")+"',";
					consulta += "'"+title.replaceAll("'", "´")+"',";
					consulta += "'"+journal_title.replaceAll("'", "´")+"',";
					consulta += "'"+pages.replaceAll("'", "´")+"',";
					consulta += "'"+volumen.replaceAll("'", "´")+"',";
					consulta += "'"+year.replaceAll("'", "´")+"',";
					consulta += "'"+issue.replaceAll("'", "´")+"',";
					consulta += "'"+doi.replaceAll("'", "´")+"',";
					consulta += "'"+pmid.replaceAll("'", "´")+"',";
					consulta += "'"+publisher_name.replaceAll("'", "´")+"',";
					consulta += "'"+tipo.replaceAll("'", "´")+"',";
					consulta += "'"+referencia.replaceAll("'", "´")+"',";
					consulta += (temp+1)*10+");";
					
					vuelta=vuelta+System.getProperty("line.separator")+consulta;
				}
			}
		}
		return vuelta;
	}
	
	private static String procesarAutores (Document doc)
	{
		String vuelta="";
		NodeList nList_princ = doc.getElementsByTagName("article-id");
		String doi_articulo="";
		for (int temp = 0; temp < nList_princ.getLength(); temp++)
		{
			Node nNode = nList_princ.item(temp);
			
			if( nNode.getNodeType()==Node.ELEMENT_NODE && buscarAtributo(nNode,"pub-id-type").equals("doi") )
			{
				doi_articulo = nNode.getTextContent();
				break;
			}
		}
		vuelta=vuelta+System.getProperty("line.separator")+("delete from revista_articulo_autor where id_articulo=(select id_articulo from "+rutasTablas.get(v_issn)+"articulo where doi like '%"+doi_articulo.substring(doi_articulo.lastIndexOf("/")+1)+"');");
		
		NodeList nList2 = doc.getElementsByTagName("contrib");
		ArrayList <String> nombres = new ArrayList<String> ();
		ArrayList <String> afiliaciones = new ArrayList<String> ();
		ArrayList <String> afiliaciones_nombres = new ArrayList<String> ();
		ArrayList <String> correspondencias = new ArrayList<String> ();
		ArrayList <String> correspondencias_nombres = new ArrayList<String> ();
		ArrayList <String> notas = new ArrayList<String> ();
		ArrayList <String> notas_nombres = new ArrayList<String> ();
		for (int temp = 0; temp < nList2.getLength(); temp++)
		{
			Node nNode = nList2.item(temp);
			NodeList hijos =nNode.getChildNodes();
			for (int temp1 = 0; temp1<hijos.getLength(); temp1++){
				Node nNode_hijo = hijos.item(temp1);
				if(nNode_hijo.getNodeName().equals("name")){
					String auxNombre="";
					NodeList cab = nNode_hijo.getChildNodes();
					
					
					for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
					{
						Node nNode_aux = cab.item(temp3);
						if( nNode_aux.getNodeType()==Node.ELEMENT_NODE && nNode_aux.getNodeName().equalsIgnoreCase("given-names") )
						{
							auxNombre = nNode_aux.getTextContent();
							break;
						}
					}
					for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
					{
						Node nNode_aux = cab.item(temp3);
						if( nNode_aux.getNodeType()==Node.ELEMENT_NODE && nNode_aux.getNodeName().equalsIgnoreCase("surname") )
						{
							auxNombre = auxNombre + " " +nNode_aux.getTextContent();
							break;
						}
					}
					nombres.add(auxNombre);
					
				}else if (nNode_hijo.getNodeName().equals("xref")){
					if(buscarAtributo(nNode_hijo,"ref-type").equals("aff")){
						while (afiliaciones.size()<nombres.size())
						{
							afiliaciones.add("");
						}
						afiliaciones.set(nombres.size()-1, buscarAtributo(nNode_hijo,"rid"));
						
					}
					if(buscarAtributo(nNode_hijo,"ref-type").equals("corresp")){
						while (correspondencias.size()<nombres.size())
						{
							correspondencias.add("");
						}
						correspondencias.set(nombres.size()-1, buscarAtributo(nNode_hijo,"rid"));
					}
					
					if(buscarAtributo(nNode_hijo,"ref-type").equals("fn")){
						while (notas.size()<nombres.size())
						{
							notas.add("");
						}
						notas.set(nombres.size()-1, buscarAtributo(nNode_hijo,"rid"));
					}
					
				}
			}
		}
		while (afiliaciones.size()<nombres.size())
		{
			afiliaciones.add("");
		}
		while (correspondencias.size()<nombres.size())
		{
			correspondencias.add("");
		}
		
		if(afiliaciones.size()>0){
			NodeList nList3 = doc.getElementsByTagName("aff");
			
			for (int af = 0; af < afiliaciones.size(); af++)
			{
				String auxNombre="";
				String nombAf=afiliaciones.get(af);
				for (int temp = 0; temp < nList3.getLength(); temp++)
				{
					Node nNode_hijo = nList3.item(temp);
					if(buscarAtributo(nNode_hijo,"id").equals(nombAf))
					{
						NodeList cab = nNode_hijo.getChildNodes();
						for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
						{
							Node nNode_aux = cab.item(temp3);
							if( nNode_aux.getNodeType()==Node.ELEMENT_NODE && nNode_aux.getNodeName().equalsIgnoreCase("institution")
									&& buscarAtributo(nNode_aux,"content-type").equals("original"))
							{
								auxNombre = nNode_aux.getTextContent();
								break;
							}
						}
					}
				}
				afiliaciones_nombres.add(auxNombre);
			}
		}
		
		if(notas.size()>0){
			NodeList nList3 = doc.getElementsByTagName("fn");
			
			for (int af = 0; af < notas.size(); af++)
			{
				String auxNombre="";
				String nombAf=notas.get(af);
				for (int temp = 0; temp < nList3.getLength(); temp++)
				{
					Node nNode_hijo = nList3.item(temp);
					if(buscarAtributo(nNode_hijo,"id").equals(nombAf))
					{
						NodeList cab = nNode_hijo.getChildNodes();
						for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
						{
							Node nNode_aux = cab.item(temp3);
							if( nNode_aux.getNodeType()==Node.ELEMENT_NODE && nNode_aux.getNodeName().equalsIgnoreCase("institution")
									&& buscarAtributo(nNode_aux,"content-type").equals("original"))
							{
								auxNombre = nNode_aux.getTextContent();
								break;
							}
						}
					}
				}
				notas_nombres.add(auxNombre);
			}
		}
		
		if(correspondencias.size()>0){
			NodeList nList3 = doc.getElementsByTagName("author-notes");
			
			for (int af = 0; af < correspondencias.size(); af++)
			{
				String auxNombre="";
				String nombAf=correspondencias.get(af);
				for (int temp = 0; temp < nList3.getLength(); temp++)
				{
					Node nNode_hijo = nList3.item(temp);
					NodeList cab = nNode_hijo.getChildNodes();
					for (int temp3 = 0; temp3 < cab.getLength(); temp3++)
					{
						Node nNode_aux = cab.item(temp3); 
						if( nNode_aux.getNodeName().equalsIgnoreCase("corresp") && buscarAtributo(nNode_aux,"id").equals(nombAf))
						{
							NodeList hijos = nNode_aux.getChildNodes();
							String contenidoTexto = "";
							for (int temp4 = 0; temp4 < hijos.getLength(); temp4++)
							{
								Node hijo = hijos.item(temp4);
								/*if( hijo.getNodeType()==Node.ELEMENT_NODE && hijo.getNodeName().equalsIgnoreCase("email"))
								{
									auxNombre = hijo.getTextContent().trim();
									break;
								}*/
								contenidoTexto = nNode_aux.getTextContent();
								contenidoTexto=contenidoTexto.replaceAll(System.lineSeparator(), "");
								while (contenidoTexto.indexOf("  ")!=-1){
									contenidoTexto=contenidoTexto.replaceAll("  ", " ");
								}
							}
							auxNombre = auxNombre.trim()+" "+contenidoTexto.trim();
						}
					}
				}
				correspondencias_nombres.add(auxNombre);
			}
			
		}
		for (int iter=0; iter<nombres.size(); iter++)
		{
			String consulta="insert into revista_articulo_autor (id_articulo, autor, afiliacion, correspondencia, orden) values (";
			consulta += "(select id_articulo from "+rutasTablas.get(v_issn)+"articulo where doi like '%"+doi_articulo.substring(doi_articulo.lastIndexOf("/")+1)+"'),";
			consulta += "'"+nombres.get(iter).replaceAll("'", "´")+"',";
			consulta += "'"+afiliaciones_nombres.get(iter).replaceAll("'", "´")+"',";
			consulta += "'"+reemplazar(correspondencias_nombres.get(iter).replaceAll("'", "´"))+"',";
			consulta += (iter+1)*10+");";
			vuelta=vuelta+System.getProperty("line.separator")+consulta;
		}
		
		for (int iter1=0; iter1<correspondencias_nombres.size(); iter1++){
			if(!correspondencias_nombres.get(iter1).trim().equalsIgnoreCase(""))
				v_correspondencias_nombres = v_correspondencias_nombres+ ", " + correspondencias_nombres.get(iter1);
		}
		if(v_correspondencias_nombres.length()>0){
			v_correspondencias_nombres=v_correspondencias_nombres.substring(2, v_correspondencias_nombres.length()).trim();
		}
		return vuelta;
	}
	
	private static String procesarBack ( Node nodo )
	{
		// Se proecesan nodos Referencias (ref-list), appendices (app)
		// Se procesa Back
		String auxStr = "";
		auxStr = "<div class=\"jats_back\" id=\"back\">"+strNotes;
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";		
		return auxStr;
	}
	
	private static String procesarFront ( Node nodo )
	{
		// Se procesa Front
		String auxStr = "";
		auxStr = "<div class=\"jats_front\" id=\"front\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";		
		return auxStr;
	}
	
	private static String procesarBody ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<div class=\"jats_body\" id=\"body\">";
		// principalmente buscamos elementos sec
		
		auxStr += procesarNodos(nodo);
		/*NodeList cab = nodo.getChildNodes();
		for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			if( nNode.getNodeType()==Node.ELEMENT_NODE )
			{
				if ( nNode.getNodeName().equalsIgnoreCase("sec") )
				{
					contSec++;
				}
				auxStr += procesarNodo(nNode);
			}
		}*/
		auxStr += "</div>";		
		return auxStr;
	}
	
	private static String procesarArticleMeta ( Node nodo )
	{
		String auxStr = "<div class=\"jats_article_meta\" id=\"article_meta\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarFecha ( Node nodo )
	{
		String day = "";
		String month = "";
		String year = "";
		String date ="";
		
		NodeList cab = nodo.getChildNodes();
		if( cab.getLength()>=1 )
		{
			for (int temp = 0; temp < cab.getLength(); temp++)
			{
				Node nNode = cab.item(temp);
				if ( nNode.getNodeType()==Node.ELEMENT_NODE )
				{
					if(nNode.getNodeName().equals("day"))
					{
						day = nNode.getTextContent();
					}
					if(nNode.getNodeName().equals("month"))
					{
						month = nNode.getTextContent();
					}
					if(nNode.getNodeName().equals("year"))
					{
						year = nNode.getTextContent();
					}
				}
			}
		}
		else
		{
			date="";
		}
		
		date=year+"-"+month+"-"+day;
		return date;
	}
	private static String procesarArticleId ( Node nodo )
	{
		String auxAtr = buscarAtributo(nodo,"pub-id-type");
		String auxCont = procesarNodos(nodo);
		String auxStr = "<div class=\"jats_article_id\">";
		if( auxAtr.equalsIgnoreCase("doi") )
		{
			v_doi_art=(auxCont.indexOf("http"))>=0?auxCont:("https://doi.org/" + auxCont);
			if( auxCont.indexOf("doi.org/")<0 )
				auxCont = "doi.org/" + auxCont; 

			if( auxCont.indexOf("http")>=0 )
				auxStr += "<a href=\"" + auxCont + "\" class=\"jats_article_id_href\">" + auxCont + "</a>";
			else
				auxStr += "<a href=\"https://" + auxCont + "\" class=\"jats_article_id_href\">https://" + auxCont + "</a>";
		}
		else
		{
			auxStr += auxCont;
		}
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarArticleCategories ( Node nodo )
	{
		String auxStr = "<div class=\"jats_article_categories\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarSubjGroup ( Node nodo )
	{
		String auxStr = "<div class=\"jats_subj_group\">" + buscarAtributo(nodo,"subj-group-type") + ":";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarSubject ( Node nodo )
	{
		String auxStr = "<span class=\"jats_subject\">";
		v_seccion=procesarNodos(nodo);
		auxStr += v_seccion;
		auxStr += "</span>";
		return auxStr;
	}
	
	private static String procesarContribGroup ( Node nodo )
	{
		String auxStr = "";
		auxStr += procesarNodos(nodo);
		return auxStr;
	}
	
	private static String procesarContrib ( Node nodo )
	{
		String[] letras= {"a","b","c","d","e","f"};
		String auxStr = "";
		String auxStr1 = "";
		Node nodo_name=getNodoHijo(nodo, "name", null, null);
		ArrayList<Node> nodo_aff=getNodosHijo(nodo, "xref", "ref-type", "aff");
		ArrayList<Node> nodo_notas=getNodosHijo(nodo, "xref", "ref-type", "fn");
		//Node nodo_corr=getNodoHijo(nodo, "xref", "ref-type", "corresp");
		Node nombre_contrib=getNodoHijo(nodo_name, "given-names", null, null);
		Node apellidos_contrib=getNodoHijo(nodo_name, "surname", null, null);
		
		Node orcid=getNodoHijo(nodo, "contrib-id", "contrib-id-type", "orcid");
		Node bio=getNodoHijo(nodo, "bio", null, null);
		
		if(bio!=null) {
			v_autores_bio=v_autores_bio+"<p>"+bio.getTextContent();
		}
		if(orcid!=null) {
			v_autores_bio=v_autores_bio+"<a href=\"https://orcid.org/"+orcid.getTextContent()+"\"><svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"20px\" height=\"20px\" viewBox=\"0 0 72 72\" version=\"1.1\">\r\n" + 
					"    <!-- Generator: sketchtool 53.1 (72631) - https://sketchapp.com -->\r\n" + 
					"    <title>Orcid logo</title>\r\n" + 
					"    <g id=\"Symbols\" stroke=\"none\" stroke-width=\"1\" fill=\"none\" fill-rule=\"evenodd\">\r\n" + 
					"        <g id=\"hero\" transform=\"translate(-924.000000, -72.000000)\" fill-rule=\"nonzero\">\r\n" + 
					"            <g id=\"Group-4\">\r\n" + 
					"                <g id=\"vector_iD_icon\" transform=\"translate(924.000000, 72.000000)\">\r\n" + 
					"                    <path d=\"M72,36 C72,55.884375 55.884375,72 36,72 C16.115625,72 0,55.884375 0,36 C0,16.115625 16.115625,0 36,0 C55.884375,0 72,16.115625 72,36 Z\" id=\"Path\" fill=\"#A6CE39\"></path>\r\n" + 
					"                    <g id=\"Group\" transform=\"translate(18.868966, 12.910345)\" fill=\"#FFFFFF\">\r\n" + 
					"                        <polygon id=\"Path\" points=\"5.03734929 39.1250878 0.695429861 39.1250878 0.695429861 9.14431787 5.03734929 9.14431787 5.03734929 22.6930505 5.03734929 39.1250878\"></polygon>\r\n" + 
					"                        <path d=\"M11.409257,9.14431787 L23.1380784,9.14431787 C34.303014,9.14431787 39.2088191,17.0664074 39.2088191,24.1486995 C39.2088191,31.846843 33.1470485,39.1530811 23.1944669,39.1530811 L11.409257,39.1530811 L11.409257,9.14431787 Z M15.7511765,35.2620194 L22.6587756,35.2620194 C32.49858,35.2620194 34.7541226,27.8438084 34.7541226,24.1486995 C34.7541226,18.1301509 30.8915059,13.0353795 22.4332213,13.0353795 L15.7511765,13.0353795 L15.7511765,35.2620194 Z\" id=\"Shape\"></path>\r\n" + 
					"                        <path d=\"M5.71401206,2.90182329 C5.71401206,4.441452 4.44526937,5.72914146 2.86638958,5.72914146 C1.28750978,5.72914146 0.0187670918,4.441452 0.0187670918,2.90182329 C0.0187670918,1.33420133 1.28750978,0.0745051096 2.86638958,0.0745051096 C4.44526937,0.0745051096 5.71401206,1.36219458 5.71401206,2.90182329 Z\" id=\"Path\"></path>\r\n" + 
					"                    </g>\r\n" + 
					"                </g>\r\n" + 
					"            </g>\r\n" + 
					"        </g>\r\n" + 
					"    </g>\r\n" + 
					"</svg>"+orcid.getTextContent()+"</a>";
		}
		if(bio!=null || orcid!=null) {
			v_autores_bio=v_autores_bio+"</p>";
		}
		
		System.out.println(v_autores_bio);
		
		/*miramos si hay alguna inicial en el nombre que venga sin punto*/
		String nomb = "";
		if(nombre_contrib!=null) {
			nomb = nombre_contrib.getTextContent();
			String[] sep_nomb = nomb.split(" ");
			String nomb_aux="";
			for(int s=0;s<sep_nomb.length; s++){
				nomb_aux=nomb_aux+" "+sep_nomb[s];
				if(sep_nomb[s].length()==1){
					nomb_aux=nomb_aux+".";
				}
				nomb=nomb_aux.substring(1);
			}
			
			auxStr=nomb+" "+apellidos_contrib.getTextContent();
			auxStr=reemplazar(auxStr);
			String sIniciales=nombre_contrib.getTextContent();
			String[] iniciales=sIniciales.split(" ");
			sIniciales="";
			for(int i=0;i<iniciales.length;i++){
				sIniciales=sIniciales+iniciales[i].toUpperCase().substring(0,1)+". ";
			}
			
			auxStr1=apellidos_contrib.getTextContent()+", "+sIniciales;
			auxStr1=reemplazar(auxStr1);
			v_autores_notag=v_autores_notag+(v_autores_notag.length()>0?", ":"")+auxStr;
			v_autores_notag_inv=v_autores_notag_inv+(v_autores_notag_inv.length()>0?"# ":"")+auxStr1;
		}
		if(nodo_aff!=null)
		{
			for(int k=0;k<nodo_aff.size();k++){
				String superindice = buscarAtributo(nodo_aff.get(k), "rid");
				superindice=superindice.replaceAll("[A-Za-z]", "");
				auxStr=auxStr+"<sup>"+(k>0?", ":"")+String.valueOf(Integer.parseInt(superindice))+"</sup>";
			}
		}
		
		if(nodo_notas!=null)
		{
			for(int k=0;k<nodo_notas.size();k++){
				String superindice = buscarAtributo(nodo_notas.get(k), "rid");
				String superoriginal=superindice;
				superindice=superindice.replaceAll("[0-9]", "");
				auxStr=auxStr+"<sup>"+(k>0?",":",")+letras[k]+"</sup>";
				notas_ref.put(superoriginal, letras[k]);
			}
		}
		return auxStr;
	}

	private static String procesarAff ( Node nodo )
	{
		String superindice=buscarAtributo(nodo, "id");
		Node nodo_aff=getNodoHijo(nodo, "institution", "content-type", "original");
		String auxStr = nodo_aff.getTextContent();
		if(superindice!=null)
		{
			superindice=superindice.replaceAll("[A-Za-z]", "");
			auxStr="<sup>"+String.valueOf(Integer.parseInt(superindice))+"</sup>"+auxStr;
		}
		return auxStr;
	}
	
	private static String procesarTitleGroup ( Node nodo )
	{
		String auxStr = "<div class=\"jats_title_group\" id=\"title_group\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarArticleTitle ( Node nodo )
	{
		String auxStr = "";
		auxStr += procesarNodos(nodo);
		return auxStr;
	}
	
	private static String procesarTransTitleGroup ( Node nodo )
	{
		String auxStr = "<div class=\"jats_trans_title_group\">" + buscarAtributo(nodo,"xml:lang") + ":";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}

	private static String procesarTransTitle ( Node nodo )
	{
		String trans_title = "";
		trans_title += procesarNodos(nodo);
		return trans_title;
	}
	
	private static String procesarAbstract ( Node nodo )
	{
		String auxStr = "";
		auxStr += procesarNodosNoTitle(nodo, "");
		return auxStr;
	}
	
	private static String procesarTransAbstract ( Node nodo )
	{
		String auxStr = "";
		auxStr += procesarNodosNoTitle(nodo, "");
		return auxStr;
	}
	
	private static String procesarRefList ( Node nodo )
	{
		String auxStr = "<div class=\"jats_ref_list\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarRef ( Node nodo )
	{
		String auxStr = "<div class=\"jats_ref\">";
		auxStr += "<a name=\"" + buscarAtributo(nodo,"id") + "\" |*external_link| >";
		auxStr += procesarNodos(nodo);
		auxStr += "</a></div>";
		String external_link ="";
		if(auxStr.indexOf("http")!=-1){
			external_link = auxStr.substring(auxStr.indexOf("http"), auxStr.length());
			if(external_link.indexOf("<")!=-1)
				external_link=external_link.substring(0, external_link.indexOf("<"));
			if(external_link.indexOf(" ")!=-1)
				external_link=external_link.substring(0, external_link.indexOf(" "));
			external_link=" href=\""+external_link+"\" target=\"_blank\" ";
		}
		auxStr=auxStr.substring(0,auxStr.indexOf("|*external_link|"))+external_link+auxStr.substring(auxStr.indexOf("|*external_link|")+"|*external_link|".length());
		return auxStr;
	}
	
	private static String procesarMixedCitation ( Node nodo )
	{
		String auxStr = "<p>";
		auxStr += procesarNodos(nodo);
		auxStr += "</p>";
		return auxStr;
	}

	private static String procesarNotes ( Node nodo )
	{
		String auxStr = "";
		return auxStr;
	}
	
	private static String procesarAuthorNotes( Node nodo) {
		String auxStr = "";
		boolean seguir=true;
		if(seguir){
			auxStr = "<div class=\"jats_notes\">" + procesarNodos(nodo) + "</div>";
		}
		return auxStr;
	}

	private static String procesarFnGroup ( Node nodo )
	{
		// TODO
		boolean seguir=true;
		NodeList cab = nodo.getChildNodes();
		if( cab.getLength()>=1 )
		{
			for (int temp = 0; temp < cab.getLength(); temp++)
			{
				Node nNode = cab.item(temp);
				if ( nNode.getNodeType()==Node.ELEMENT_NODE )
				{
					if( nNode.getNodeName().equalsIgnoreCase("title")){
						if(nNode.getTextContent().toUpperCase().equalsIgnoreCase("Extend Summary".toUpperCase()) ||
							nNode.getTextContent().toUpperCase().equalsIgnoreCase("Extend Abstract".toUpperCase())){
							String contentExtended=procesarNodos(nodo);
							contentExtended=contentExtended.replaceAll("'", "&#39;");
							if(contentExtended.indexOf("jats_fn")!=-1)
								contentExtended=contentExtended.replaceAll("jats_fn", "");
							otherQuery = otherQuery+System.getProperty("line.separator")+"update "+rutasTablas.get(v_issn)+"articulo set extended_abstract='"+contentExtended+"' where id_articulo=|*id*|";
							v_contentExtended=contentExtended;
							seguir=false;
						}
					}
				}
			}
		}
		String auxStr = "";
		if(seguir){
			auxStr = "<div class=\"jats_fn_group\">" + procesarNodos(nodo) + "</div>";
		}
		return auxStr;
	}
	
	private static String procesarFn ( Node nodo )
	{
		// TODO
		String v_aux =procesarNodos(nodo);
		String auxStr = "";
		auxStr = "<div class=\"jats_fn\">" + v_aux + "</div>";
		return auxStr;
	}
	
	private static String procesarGlossary ( Node nodo )
	{
		// TODO
		String auxStr = "";
		return auxStr;
	}
	
	private static String procesarFloatsGroup ( Node nodo )
	{
		// TODO
		String auxStr = "";
		return auxStr;
	}
	
	private static String procesarTitle ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<p class=\"jats_title\">" + procesarNodos(nodo) + "</p>";
		return auxStr;
	}
	
	private static String procesarLabel ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<span class=\"jats_label\" alt=\"" + buscarAtributo(nodo,"alt") + "\">" + procesarNodos(nodo) + "</span>";
		return auxStr;
	}
	
	private static String procesarTable ( Node nodo )
	{
		String tipoListaStr = "ol";
		String tipoListaStr1 = "</ol>";
		String clase =" class=\"jats_table\" ";
		Node nodo_tr=getNodoHijo(nodo, "tr", null, null);
		Node nodo_tbody= getNodoHijo(nodo, "tbody", null, null);
		if(nodo_tr!=null || nodo_tbody!=null)
		{
			tipoListaStr="table";
			tipoListaStr1 = "</table>";
			clase="";
		}
		else
		{
			String tipoLista = buscarAtributo(nodo,"list-type");
			String value = buscarAtributo(nodo,"value");
			if(value!=null && !value.equalsIgnoreCase("")){
				value=" value=\""+value+"\" ";
			}
			if (tipoLista.equals("alpha-lower")){
				tipoListaStr = "ol type=\"a\"";
				tipoListaStr1 = "</ol>";
			}
			if (tipoLista.equals("alpha-upper")){
				tipoListaStr = "ol type=\"A\"";
				tipoListaStr1 = "</ol>";
			}
			if (tipoLista.equals("roman-lower)")){
				tipoListaStr = "ol type=\"i\"";
				tipoListaStr1 = "</ol>";
			}
			if (tipoLista.equals("roman-upper)")){
				tipoListaStr = "ol type=\"I\"";
				tipoListaStr1 = "</ol>";
			}
			if (tipoLista.equals("order)")){
				tipoListaStr = "ol type=\"1\"";
				tipoListaStr1 = "</ol>";
			}
			if (tipoLista.equalsIgnoreCase("bullet") || tipoLista.equalsIgnoreCase("simple")){
				tipoListaStr = "ul";
				if(tipoLista.equalsIgnoreCase("simple")){
					tipoListaStr = tipoListaStr +" style =\"list-style-type: none\" ";
				}
			}
			
			
			if ( tipoLista.equalsIgnoreCase("bullet") || tipoLista.equalsIgnoreCase("simple") )
				tipoListaStr = "ul";
		}
		String auxStr = "<" + tipoListaStr + clase  + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += tipoListaStr1;
		return auxStr;
	}

	private static String procesarList ( Node nodo )
	{
		String tipoListaStr = "ul";
		String tipoListaStr1 = "</ul>";
		String tipoLista = buscarAtributo(nodo,"list-type");
		String value = buscarAtributo(nodo,"value");
		if(value!=null && !value.equalsIgnoreCase("")){
			value=" value=\""+value+"\" ";
		}
		if (tipoLista.equals("alpha-lower")){
			tipoListaStr = "ol type=\"a\"";
			tipoListaStr1 = "</ol>";
		}
		if (tipoLista.equals("alpha-upper")){
			tipoListaStr = "ol type=\"A\"";
			tipoListaStr1 = "</ol>";
		}
		if (tipoLista.equals("roman-lower)")){
			tipoListaStr = "ol type=\"i\"";
			tipoListaStr1 = "</ol>";
		}
		if (tipoLista.equals("roman-upper)")){
			tipoListaStr = "ol type=\"I\"";
			tipoListaStr1 = "</ol>";
		}
		if (tipoLista.equals("order)")){
			tipoListaStr = "ol type=\"1\"";
			tipoListaStr1 = "</ol>";
		}
		if (tipoLista.equalsIgnoreCase("bullet") || tipoLista.equalsIgnoreCase("simple")){
			tipoListaStr = "ul";
			if(tipoLista.equalsIgnoreCase("simple")){
				tipoListaStr = tipoListaStr +" style =\"list-style-type: none\" ";
			}
		}
		
		String auxStr = "<"+tipoListaStr+value+" class=\"jats_list\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += tipoListaStr1;
		return auxStr;
	}
	
	private static String procesarListItem ( Node nodo )
	{
		String auxStr = "<li class=\"jats_list_item\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</li>";
		return auxStr;
	}
	
	private static String procesarCol ( Node nodo )
	{
		String auxStr = "<col class=\"jats_col\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</col>";
		return auxStr;
	}

	private static String procesarColGroup ( Node nodo )
	{
		String auxStr = "<colgroup class=\"jats_colgroup\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</colgroup>";
		return auxStr;
	}

	private static String procesarTh ( Node nodo )
	{
		String auxStr = "<th class=\"jats_th\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</th>";
		return auxStr;
	}

	private static String procesarTr ( Node nodo )
	{
		Node nodo_tr=getNodoHijo(nodo, "td", null, null);
		String clase=" class=\"jats_tr\" ";
		if(nodo_tr!=null)
		{
			clase="";
		}
		String auxStr = "<tr "+clase + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</tr>";
		return auxStr;
	}
	
	
	private static String procesarTd ( Node nodo )
	{
		String auxStr = "<td " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</td>";
		return auxStr;
	}

	private static String procesarTHead ( Node nodo )
	{
		String auxStr = "<thead class=\"jats_thead\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</thead>";
		return auxStr;
	}

	private static String procesarTFoot ( Node nodo )
	{
		String auxStr = "<tfoot class=\"jats_tfoot\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</tfoot>";
		return auxStr;
	}

	private static String procesarTBody ( Node nodo )
	{
		String auxStr = "<tbody class=\"jats_tbody\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</tbody>";
		return auxStr;
	}

	private static String procesarTableWrap ( Node nodo )
	{
		String auxStr = "<div class=\"jats_table_wrap\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarFig ( Node nodo )
	{
		String auxStr = "<div class=\"jats_table_wrap\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}

	private static String procesarTableWrapFoot ( Node nodo )
	{
		String auxStr = "<div class=\"jats_table_wrap_foot\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}

	private static String procesarTableWrapGroup ( Node nodo )
	{
		String auxStr = "<div class=\"jats_table_wrap_group\" " + pintarAllAtributos(nodo) + "\"><a name=\"" + buscarAtributo(nodo,"id") + "></a>";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}

	private static String procesarDispFormula ( Node nodo )
	{
		String auxStr = "<div class=\"jats_disp_formula\" " + pintarAllAtributos(nodo) + "><a name=\"" + buscarAtributo(nodo,"id") + "\"></a>";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}

	private static String procesarGraphic ( Node nodo )
	{
		String auxStr = "<a href=\"/"+v_prefijo+"/jats_files/" + buscarAtributo(nodo,"xlink:href") + "\" target=\"_blank\"><img class=\"jats_graphic\" src=\"/"+v_prefijo+"/jats_files/" + buscarAtributo(nodo,"xlink:href") + "\" title=\"" + buscarAtributo(nodo,"xlink:title") + "\"></a>";
		auxStr=auxStr.replaceAll("//", "/");
		auxStr += procesarNodos(nodo);
		auxStr += "</img>";
		return auxStr;
	}

	private static String procesarInlineGraphic ( Node nodo )
	{
		String auxStr = "<a href=\"/"+v_prefijo+"/jats_files/" + buscarAtributo(nodo,"xlink:href") + "\" target=\"_blank\"><img class=\"jats_graphic_inline\" src=\"/"+v_prefijo+"/jats_files/" + buscarAtributo(nodo,"xlink:href") + "\" title=\"" + buscarAtributo(nodo,"xlink:title") + "\"></a>";
		auxStr=auxStr.replaceAll("//", "/");
		auxStr += procesarNodos(nodo);
		auxStr += "</img>";
		return auxStr;
	}

	
	private static String procesarCaption ( Node nodo )
	{
		String auxStr = "<div class=\"jats_caption\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarAttrib ( Node nodo )
	{
		String auxStr = "<div class=\"jats_attrib\">";
		auxStr += procesarNodos(nodo);
		auxStr += "</div>";
		return auxStr;
	}
	
	private static String procesarVerseGroup ( Node nodo )
	{
		String auxStr = "<span class=\"jats_verse\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</span>";
		return auxStr;
	}
	
	private static String procesarVerseLine ( Node nodo )
	{
		String auxStr = "<p class=\"jats_line\" " + pintarAllAtributos(nodo) + ">";
		auxStr += procesarNodos(nodo);
		auxStr += "</p>";
		return auxStr;
	}
	
	private static String procesarName ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<span class=\"jats_name\">";
		
		String auxNombre = "";
		NodeList cab = nodo.getChildNodes();
		for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			if( nNode.getNodeType()==Node.ELEMENT_NODE && nNode.getNodeName().equalsIgnoreCase("surname") )
			{
				auxNombre = nNode.getTextContent();
				break;
			}
		}
		for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			if( nNode.getNodeType()==Node.ELEMENT_NODE && nNode.getNodeName().equalsIgnoreCase("given-names") )
			{
				if (auxNombre.equalsIgnoreCase(""))
				{
					auxNombre = nNode.getTextContent();
				}
				else
				{
					auxNombre += ", " + nNode.getTextContent();
				}
				break;
			}
		}
		auxStr += auxNombre;
		auxStr += "</span>";
		return auxStr;
	}
	
	private static String procesarEmail ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<a href=\"mailto:" + nodo.getTextContent() + "\" class=\"jats_email\">" + nodo.getTextContent() + "</a>";
		return auxStr;
	}
	
	private static String procesarExtLink ( Node nodo )
	{
		String auxStr = "";
		String link =buscarAtributo(nodo,"xlink:href");
		if(link==null || link.equalsIgnoreCase("")) {
			link=nodo.getTextContent();
		}
		auxStr = "<a href=\"" + link + "\" class=\"jats_ext-link\">" + nodo.getTextContent() + "</a>";
		return auxStr;
	}
	
	private static String procesarUri ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<a href=\"" + nodo.getTextContent() + "\" class=\"jats_uri\">" + nodo.getTextContent() + "</a>";
		return auxStr;
	}
	
	private static String procesarXRef ( Node nodo )
	{
		String auxStr = "";
		String auxTipo = buscarAtributo(nodo,"ref-type");
		auxStr = "<a href=\"#" + buscarAtributo(nodo,"rid") + "\" alt=\"" + buscarAtributo(nodo,"alt") + "\" class=\"jats_xref_" + auxTipo + "\">" + procesarNodos(nodo) + "</a>";
		return auxStr;
	}
	
	private static String procesarItalic ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<i>" + procesarNodos(nodo) + "</i>";
		return auxStr;
	}
	
	
	private static String procesarOverline ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<span style=\"text-decoration:overline\">" + procesarNodos(nodo) + "</span>";
		return auxStr;
	}
	
	private static String procesarBold ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<b>" + procesarNodos(nodo) + "</b>";
		return auxStr;
	}

	private static String procesarUnderline ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<u>" + procesarNodos(nodo) + "</u>";
		return auxStr;
	}
	
	private static String procesarSup ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<sup>" + procesarNodos(nodo) + "</sup>";
		return auxStr;
	}
	
	private static String procesarSub ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<sub>" + procesarNodos(nodo) + "</sub>";
		return auxStr;
	}

	
	private static String procesarP ( Node nodo )
	{
		Node padre = nodo.getParentNode();
		String auxStr = "";
		
		/*buscamos si tiene una referencia*/
		String ref=buscarAtributo(padre,"id");
		String super_indice="";
		if(ref!=null && !ref.equalsIgnoreCase("")) {
			super_indice=notas_ref.get(ref);
			if(super_indice!=null && !super_indice.equalsIgnoreCase("")) {
				super_indice="<sup>"+super_indice+"</sup>";
			}else {
				super_indice="";
			}
		}
		
		if( padre.getNodeName().equalsIgnoreCase("list-item") )
			auxStr = procesarNodos(nodo);
		else
			auxStr = "<p class=\"jats_p\">" + super_indice+ procesarNodos(nodo) + "</p>";
		return auxStr;
	}

	
	private static String procesarSecMeta ( Node nodo )
	{
		String auxStr = "";
		return auxStr;
	}
	
	private static String procesarAck ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<div class=\"jats_ack\">" + procesarNodos(nodo) + "</div>";
		return auxStr;
	}
	
	private static String procesarAppGroup ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<div class=\"jats_app_group\">" + procesarNodos(nodo) + "</div>";
		return auxStr;
	}
	
	private static String procesarApp ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<a name=\"" + buscarAtributo(nodo,"id") + "\"></a>" + procesarNodos(nodo);
		return auxStr;
	}
	
	private static String procesarBio ( Node nodo )
	{
		String auxStr = "";
		return auxStr;
	}
	
	private static String procesarFundingGroup ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<div class=\"jats_funding_group\">" + procesarNodos(nodo) + "</div>";
		return auxStr;
	}
	
	private static String procesarAwardGroup ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<div class=\"jats_award_group\">" + procesarNodos(nodo) + "</div>";
		return auxStr;
	}
	
	private static String procesarFundingSource ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<span class=\"jats_funding_source\">" + procesarNodos(nodo) + "</span>";
		return auxStr;
	}
	
	private static String procesarAwardId ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<span class=\"jats_award_id\">(" + procesarNodos(nodo) + ")</span>";
		return auxStr;
	}
	
	private static String procesarFundingStatement ( Node nodo )
	{
		String auxStr = "";
		auxStr = "<div class=\"jats_funding_statement\">" + procesarNodos(nodo) + "</div>";
		return auxStr;
	}
	
	private static String procesarKwdGroup ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodosNoTitle(nodo, ", ");
		return auxStr;
	}
	
	
	private static String procesarCopyrightYear ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodosNoTitle(nodo, ", ");
		return auxStr;
	}
	
	private static String procesarKwd ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodosNoTitle(nodo, ", ");
		return auxStr;
	}
	
	private static String procesarVolume ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodos(nodo);
		return auxStr;
	}

	private static String procesarIssue ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodos(nodo);
		return auxStr;
	}

	private static String procesarlPage ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodos(nodo);
		return auxStr;
	}

	private static String procesarfPage ( Node nodo )
	{
		String auxStr = "";
		auxStr = procesarNodos(nodo);
		return auxStr;
	}
	
	private static String procesarJournalMeta ( Node nodo )
	{
		String auxStr = "";
		procesarNodos(nodo);
		return auxStr;
	}
	
	private static String procesarIssn ( Node nodo )
	{
		String auxStr = "";
		if( buscarAtributo(nodo,"pub-type").equalsIgnoreCase("epub") )
		{
			issn2 = "<span class=\"jats_issn\">(" + procesarNodos(nodo) + ")</span>";
		}
		else
		{
			issn = "<span class=\"jats_issn\">" + procesarNodos(nodo) + "</span>";
		}
		return auxStr;
	}
	private static void procesarIssn1 ( Node nodo )
	{
		if( buscarAtributo(nodo,"pub-type").equalsIgnoreCase("epub") )
		{
			if(v_issn==null || v_issn.equals("")) {
				v_issn = procesarNodos(nodo);
				v_prefijo = rutas.get(v_issn);
			}
		}
		else
		{
			v_issn = procesarNodos(nodo);
			v_prefijo = rutas.get(v_issn);
		}
	}

	private static String procesarJournalTitleGroup ( Node nodo )
	{
		String auxStr = "";
		NodeList cab = nodo.getChildNodes();
		// buscamos la etiqueta - label
		for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			if( nNode.getNodeType()==Node.ELEMENT_NODE )
			{
				if ( nNode.getNodeName().equalsIgnoreCase("journal-title") )
				{
					//journalTitle = "<span class=\"jats_journal_title\">" + nNode.getTextContent() + "</span>";
					v_journalTitle = nNode.getTextContent();
				}
			}
		}
		return auxStr;
	}
	
	private static String procesarPublisherName ( Node nodo )
	{
		String auxStr = "";
		NodeList cab = nodo.getChildNodes();
		// buscamos la etiqueta - label
		for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			if( nNode.getNodeType()==Node.ELEMENT_NODE )
			{
				if ( nNode.getNodeName().equalsIgnoreCase("publisher-name") )
				{
					publisherName = "<span class=\"jats_publisher_name\">" + nNode.getTextContent() + "</span>";
				}
			}
		}
		return auxStr;
	}
	
	private static String procesarSec ( Node nodo )
	{
		String separador="<br>";
		if(v_issn.equalsIgnoreCase("1989-9912"))
			separador="<hr>";
		String auxStr = "";
		String secType = ""; 
		NodeList cab = nodo.getChildNodes();
		String auxTitulo = "";
		
		// Cogemos el attribuyo sec-type para realizar la hipervinculo
		secType = buscarAtributo(nodo,"sec-type");
		auxTitulo = secType; 
		
		if( !secType.trim().equalsIgnoreCase("") )
		{
			// Tenemos una seccion con ID
			// Debemos encontar el primer nodo que sea un Title para crear el hiperviculo
			for (int temp = 0; temp < cab.getLength(); temp++)
			{
				Node nNode = cab.item(temp);
				if( nNode.getNodeType()==Node.ELEMENT_NODE && nNode.getNodeName().equalsIgnoreCase("title") )
				{
					//Extended Summary
					auxTitulo = nNode.getTextContent();
					nodo.removeChild(nNode);
					break;
				}
			}
			auxStr = "<p class=\"jats_sec jats_sec1\"><a name=\"" + secType.toLowerCase().replaceAll(" ", "") + "\" class=\"jats_sec jats_sec1\">" + auxTitulo + "</a></p>";
			strIndexSec += "<a href=\"#" + secType.toLowerCase().replaceAll(" ", "") + "\" class=\"jats_sec_href\">" + auxTitulo + "</a>"+separador; 
			
		}else {
			// Tenemos una seccion con ID
			// Debemos encontar el primer nodo que sea un Title para crear el hiperviculo
			String nomb_padre=nodo.getParentNode().getNodeName();
		    
			//Node nodo_sec_secundario=getNodoHijo(nodo, "sec", null, null);
			String auxLink="";
			if(!nomb_padre.equalsIgnoreCase("sec")) {
				for (int temp = 0; temp < cab.getLength(); temp++)
				{
					Node nNode = cab.item(temp);
					if( nNode.getNodeType()==Node.ELEMENT_NODE && nNode.getNodeName().equalsIgnoreCase("title") )
					{
						//Extended Summary
						auxTitulo = nNode.getTextContent();
						String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
					    // Cadena de caracteres ASCII que reemplazarán los originales.
					    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
					    String output = auxTitulo;
					    for (int i=0; i<original.length(); i++) {
					        // Reemplazamos los caracteres especiales.
					        output = output.replace(original.charAt(i), ascii.charAt(i));
					    }
					    auxLink=output;
						secType=auxTitulo;
						nodo.removeChild(nNode);
						break;
					}
				}
				auxStr = "<p class=\"jats_sec jats_sec1\"><a name=\"" + auxLink.toLowerCase().replaceAll(" ", "") + "\" class=\"jats_sec jats_sec1\">" + auxTitulo + "</a></p>";
				
				strIndexSec += "<a href=\"#" + auxLink.toLowerCase().replaceAll(" ", "") + "\" class=\"jats_sec_href\">" + auxTitulo + "</a>"+separador;
			}
			
		}
			
			
		//System.out.println("### SEC: " + nodo.toString() + " - " + auxTitulo + " - " + secType);
		
		
		auxStr += procesarNodos(nodo);
		/*for (int temp = 0; temp < cab.getLength(); temp++)
		{
			Node nNode = cab.item(temp);
			if( nNode.getNodeType()==Node.ELEMENT_NODE )
			{
				auxStr += procesarNodos(nNode);
			}
		}*/
		//System.out.println("### SEC [RETURN]: " + auxStr);
		if(auxTitulo.toLowerCase().equalsIgnoreCase("extended summary") || auxTitulo.toLowerCase().equalsIgnoreCase("extended abstract")){
			
			otherQuery = otherQuery+System.getProperty("line.separator")+"update "+rutasTablas.get(v_issn)+"articulo set extended_abstract='"+auxStr.replaceAll("jats_sec","navbar-brand").replaceAll(secType.toLowerCase().replaceAll(" ", ""), "ext")+"' where id_articulo=|*id*|";
			v_contentExtended=auxStr.replaceAll("jats_sec","navbar-brand").replaceAll(secType.toLowerCase().replaceAll(" ", ""), "ext");
			v_contentExtended=v_contentExtended.replaceAll("'", "&#39;");
		}
		return auxStr;
	}
	
	private static String procesarTagInicio ( Node nodo )
	{
		//System.out.println("### CTRL: " + nodo.toString());
		String auxStr = "";
		auxStr = "<" + nodo.getNodeName();
		
		// Analizamos los atributos
		NamedNodeMap attributes = nodo.getAttributes();
		for (int temp2 = 0; temp2<attributes.getLength(); temp2++)
		{
			Node att = attributes.item(temp2);
			auxStr += " " + att.getNodeName() + "=\"" + att.getNodeValue() + "\"";
		}
		auxStr += " class=\"jats_" + nodo.getNodeName() + "\">";
		return auxStr;
	}
	
	private static String procesarTagFin ( Node nodo )
	{
		String auxStr = "";
		auxStr += "</" + nodo.getNodeName() + ">";
		return auxStr;
	}
	
	private static String procesarNodo ( Node nodo )
	{
		String auxStr = "";
		//System.out.println("# NODO " + nodo.getNodeName() + "[" + nodo.getNodeType() + "]" + nodo.getTextContent());
		if ( nodo.getNodeType()==Node.ELEMENT_NODE )
		{
			/*cambio para inline-formulas*/
			if ( nodo.getNodeName().equalsIgnoreCase("inline-formula"))
			{
				// Set up the output transformer
				try {
			      TransformerFactory transfac = TransformerFactory.newInstance();
			      Transformer trans = transfac.newTransformer();
			      trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			      trans.setOutputProperty(OutputKeys.INDENT, "yes");
			      // Print the DOM node
			      StringWriter sw = new StringWriter();
			      StreamResult result = new StreamResult(sw);
			      DOMSource source = new DOMSource(nodo);
			      trans.transform(source, result);
			      String xmlString = sw.toString();
			      System.out.println(xmlString);
			      xmlString=xmlString.replaceAll("mml:", "");
			      System.out.println(xmlString);
			      auxStr +=xmlString;
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			/*cambio para inline-formulas*/
			if ( nodo.getNodeName().equalsIgnoreCase("disp-formula"))
			{
				auxStr = "<div class=\"jats_disp_formula\" " + pintarAllAtributos(nodo) + "><a name=\"" + buscarAtributo(nodo,"id") + "\"></a>";
				// Set up the output transformer
				try {
			      TransformerFactory transfac = TransformerFactory.newInstance();
			      Transformer trans = transfac.newTransformer();
			      trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			      trans.setOutputProperty(OutputKeys.INDENT, "yes");
			      // Print the DOM node
			      StringWriter sw = new StringWriter();
			      StreamResult result = new StreamResult(sw);
			      DOMSource source = new DOMSource(nodo);
			      trans.transform(source, result);
			      String xmlString = sw.toString();
			      System.out.println(xmlString);
			      xmlString=xmlString.replaceAll("mml:", "");
			      System.out.println(xmlString);
			      auxStr +=xmlString;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				auxStr += "</div>";
			}
			
			if ( nodo.getNodeName().equalsIgnoreCase("pub-date"))
			{
				String pub_date_date = buscarAtributo(nodo,"pub-type");
				if(!pub_date_date.equalsIgnoreCase("collection") && (v_pubdate==null || v_pubdate.equalsIgnoreCase("")))
					v_pubdate = procesarFecha(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("date"))
			{
				
				String type_date = buscarAtributo(nodo,"date-type");
				String aux_v_pubdate = procesarFecha(nodo);
				if(type_date.equals("received"))
				{
					v_receiveddate=aux_v_pubdate;
				}
				if(type_date.equals("accepted"))
				{
					v_accepteddate=aux_v_pubdate;
				}
			}
			
			if ( nodo.getNodeName().equalsIgnoreCase("history"))
			{
				procesarNodos(nodo);
			}
			
			if ( nodo.getNodeName().equalsIgnoreCase("article-id") )
			{
				// tenemos un Article-ID
				auxStr += procesarArticleId(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("article-meta") )
			{
				// tenemos un title
				auxStr += procesarArticleMeta(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("article-categories") )
			{
				// tenemos un Article Categories
				auxStr += procesarArticleCategories(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("subj-group") )
			{
				// tenemos un Subject Group
				auxStr += procesarSubjGroup(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("subject") )
			{
				// tenemos un Subject
				auxStr += procesarSubject(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("contrib-group") )
			{
				// tenemos un Contrib-Group
				procesarContribGroup(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("contrib") )
			{
				// tenemos un Contrib
				v_autores =v_autores+(v_autores.length()>0?", ":"")+procesarContrib(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("aff") )
			{
				// tenemos un Contrib
				v_afiliaciones =v_afiliaciones+(v_afiliaciones.length()>0?"; ":"")+procesarAff(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("title-group") )
			{
				// tenemos un Title Group
				procesarTitleGroup(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("abstract") )
			{
				// tenemos un Article Title
				v_abstract=procesarAbstract(nodo);
				v_abstract = reemplazar(v_abstract);
				v_abstract = v_abstract.replaceAll("'", "&apos;");
			}
			if ( nodo.getNodeName().equalsIgnoreCase("trans-abstract") )
			{
				// tenemos un Article Title
				String v_lang_sec = buscarAtributo(nodo,"xml:lang");
				if(v_lang_sec.equals("pt")) {
					v_resumo=procesarTransAbstract(nodo);
					v_resumo = reemplazar(v_resumo);
					v_resumo = v_resumo.replaceAll("'", "&apos;");
				}else {
					v_trans_abstract=procesarTransAbstract(nodo);
					v_trans_abstract = reemplazar(v_trans_abstract);
					v_trans_abstract = v_trans_abstract.replaceAll("'", "&apos;");
				}
			}
			if ( nodo.getNodeName().equalsIgnoreCase("article-title") )
			{
				// tenemos un Article Title
				v_title = procesarArticleTitle(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("trans-title-group") )
			{
				v_lang_sec = buscarAtributo(nodo,"xml:lang");
				// tenemos un Trans Title Group
				procesarTransTitleGroup(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("trans-title") )
			{
				// tenemos un Trans Title
				Node nodoPadre=nodo.getParentNode();
				String idioma_padre=buscarAtributo(nodoPadre,"xml:lang");
				if(idioma_padre!=null && idioma_padre.equals("pt"))
					v_trans_title2 = procesarTransTitle(nodo);
				else	
					v_trans_title = procesarTransTitle(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("title") )
			{
				// tenemos un title
				auxStr += procesarTitle(nodo);
			}
			if ( nodo.getNodeName().equalsIgnoreCase("label") )
			{
				// tenemos un label
				auxStr += procesarLabel(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("p") )
			{
				// tenemos un parrafo
				auxStr += procesarP(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("sec") )
			{
				// tenemos una seccion dentro de otra
				auxStr += procesarSec(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("xref") )
			{
				// tenemos un xref
				auxStr += procesarXRef(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("ext-link") )
			{
				// tenemos un xref
				auxStr += procesarExtLink(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("bold") )
			{
				// tenemos un bold
				auxStr += procesarBold(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("underline") )
			{
				// tenemos un bold
				auxStr += procesarUnderline(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("italic") )
			{
				// tenemos un italic
				auxStr += procesarItalic(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("overline") )
			{
				// tenemos un italic
				auxStr += procesarOverline(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("sub") )
			{
				// tenemos un sub
				auxStr += procesarSub(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("sup") )
			{
				// tenemos un sup
				auxStr += procesarSup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("table") )
			{
				// tenemos un table
				auxStr += procesarTable(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("col") )
			{
				// tenemos un col
				auxStr += procesarCol(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("colgroup") )
			{
				// tenemos un colgroup
				auxStr += procesarColGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("th") )
			{
				// tenemos un th
				auxStr += procesarTh(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("tr") )
			{
				// tenemos un tr
				auxStr += procesarTr(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("td") )
			{
				// tenemos un tr
				auxStr += procesarTd(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("thead") )
			{
				// tenemos un thead
				auxStr += procesarTHead(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("tfoot") )
			{
				// tenemos un tfoot
				auxStr += procesarTFoot(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("tbody") )
			{
				// tenemos un tbody
				auxStr += procesarTBody(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("disp-formula") )
			{
				// tenemos un disp-formula
				//auxStr += procesarDispFormula(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("table-wrap") )
			{
				// tenemos un table-wrap
				auxStr += procesarTableWrap(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("table-wrap-foot") )
			{
				// tenemos un table-wrap-foot
				auxStr += procesarTableWrapFoot(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("table-wrap-group") )
			{
				// tenemos un table-wrap-group
				auxStr += procesarTableWrapGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("list") )
			{
				// tenemos un table-wrap-group
				auxStr += procesarList(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("list-item") )
			{
				// tenemos un table-wrap-group
				auxStr += procesarListItem(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("fig") )
			{
				// tenemos un table-wrap
				auxStr += procesarFig(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("name") )
			{
				// tenemos un name
				auxStr += procesarName(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("email") )
			{
				// tenemos un email
				auxStr += procesarEmail(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("graphic") )
			{
				// tenemos un graphic
				auxStr += procesarGraphic(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("inline-graphic") )
			{
				// tenemos un graphic
				auxStr += procesarInlineGraphic(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("caption") )
			{
				// tenemos un caption
				auxStr += procesarCaption(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("attrib") )
			{
				// tenemos un caption
				auxStr += procesarAttrib(nodo);
			}
			
			else if ( nodo.getNodeName().equalsIgnoreCase("verse-group") )
			{
				// tenemos un caption
				auxStr += procesarVerseGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("verse-line") )
			{
				// tenemos un caption
				auxStr += procesarVerseLine(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("ref-list") )
			{
				// tenemos una lista de referencias
				auxStr += procesarRefList(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("ref") )
			{
				// tenemos una lista de referencias
				auxStr += procesarRef(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("mixed-citation") )
			{
				// tenemos una lista de referencias
				auxStr += procesarMixedCitation(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("sec-meta") )
			{
				// tenemos una cabecera de la sección que debería ser única
				auxStr += procesarSecMeta(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("fn-group") )
			{
				// tenemos un pie de nota
				auxStr += procesarFnGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("fn") )
			{
				// tenemos un pie de nota
				auxStr += procesarFn(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("glossary") )
			{
				// tenemos un glosario
				auxStr += procesarGlossary(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("app-group") )
			{
				// tenemos un app-group
				auxStr += procesarAppGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("app") )
			{
				// tenemos un app-group
				auxStr += procesarApp(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("ack") )
			{
				// tenemos un ack
				auxStr += procesarAck(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("bio") )
			{
				// tenemos un bio
				auxStr += procesarBio(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("notes") )
			{

				auxStr += procesarNotes(nodo);
			}
			else if (nodo.getNodeName().equalsIgnoreCase("author-notes") )
			{
				strNotes += procesarAuthorNotes(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("funding-group") )
			{

				auxStr += procesarFundingGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("award-group") )
			{

				auxStr += procesarAwardGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("funding-source") )
			{

				auxStr += procesarFundingSource(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("award-id") )
			{

				auxStr += procesarAwardId(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("funding-statement") )
			{

				auxStr += procesarFundingStatement(nodo);
			}			
			else if ( nodo.getNodeName().equalsIgnoreCase("kwd-group") )
			{
				String aux_v_lang = buscarAtributo(nodo,"xml:lang");
				if(aux_v_lang.equals("en"))
					v_keywords=procesarKwdGroup(nodo);
				else
					if(aux_v_lang.equals("pt"))
						v_palabras_chave=procesarKwdGroup(nodo);
					else
						v_palabras_clave=procesarKwdGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("copyright-year") )
			{
				v_copyright_year += procesarCopyrightYear(nodo);
			} 
			else if ( nodo.getNodeName().equalsIgnoreCase("kwd") )
			{
				auxStr += procesarKwd(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("volume") )
			{
				v_volume=procesarVolume(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("issue") )
			{
				v_issue = procesarIssue(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("fpage") )
			{

				v_fpage=procesarfPage(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("lpage") )
			{

				v_lpage=procesarlPage(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("elocation-id") )
			{

				v_elocation_id=procesarlPage(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("issn") )
			{

				procesarIssn1(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("journal-meta") )
			{

				procesarJournalMeta(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("journal-title-group") )
			{

				procesarJournalTitleGroup(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("disp-quote") )
			{

				auxStr +=procesarDispQuote(nodo);
			}
			else if ( nodo.getNodeName().equalsIgnoreCase("publisher") )
			{

				//auxStr += procesarPublisherName(nodo);
			}
			
			else
			{
				//auxStr += procesarNodo( nodo );
				//auxStr += nodo.getTextContent();
			}
		}
		else
		{
			auxStr += nodo.getTextContent();
		}
		return auxStr;
	}

	private static String procesarDispQuote(Node nodo) {
		// TODO Auto-generated method stub
		
		return "<p class=\"jats_fn\">" + nodo.getTextContent() + "</p>"; 
		
	}

	private static String procesarNodos ( Node nodo )
	{
		String auxStr = "";
		// Analizamos el interior del nodo, buscamos tipos reconocibles y tratables
		NodeList cab = nodo.getChildNodes();
		if( cab.getLength()>=1 )
		{
			for (int temp = 0; temp < cab.getLength(); temp++)
			{
				Node nNode = cab.item(temp);
				if ( nNode.getNodeType()==Node.ELEMENT_NODE )
				{
					auxStr += procesarNodo( nNode );
				}
				else
				{
					auxStr += nNode.getTextContent();
				}
			}
		}
		else
		{
			// No encontramos elementos, quizás sea texto
			auxStr += nodo.getTextContent();
		}
		return auxStr;
	}
	
	private static String procesarNodosNoTitle ( Node nodo , String separador)
	{
		String auxStr = "";
		// Analizamos el interior del nodo, buscamos tipos reconocibles y tratables
		NodeList cab = nodo.getChildNodes();
		if( cab.getLength()>=1 )
		{
			for (int temp = 0; temp < cab.getLength(); temp++)
			{
				Node nNode = cab.item(temp);
				if ( nNode.getNodeType()==Node.ELEMENT_NODE )
				{
					if(!nNode.getNodeName().equalsIgnoreCase("title"))
					{
						if(!nNode.getNodeName().equalsIgnoreCase("p"))
						{
							String valor=procesarNodo( nNode ).trim();
							if(!valor.equalsIgnoreCase(""))
								auxStr += valor + separador;
						}
						else
						{
							if(!nNode.getTextContent().trim().equalsIgnoreCase("")){
								String valor=procesarNodo( nNode ).trim();
								if(!valor.equalsIgnoreCase(""))
									auxStr += valor + separador;
							}
						}
					}
				}
				else
				{
					if(!nNode.getTextContent().trim().equalsIgnoreCase(""))
						auxStr += nNode.getTextContent().trim() + separador;
				}
			}
		}
		else
		{
			// No encontramos elementos, quizás sea texto
			String contenido= nodo.getTextContent();
			contenido = reemplazar(contenido);
			auxStr += contenido + separador;
		}
		if(auxStr.indexOf(separador)!=-1){
			auxStr=auxStr.substring(0, auxStr.lastIndexOf(separador));
		}
		return auxStr;
	}
	
	private static Node getNodoHijo ( Node nodo , String hijo, String atributo, String valorAtributo)
	{
		Node nodo_return = null;
		// Analizamos el interior del nodo, buscamos tipos reconocibles y tratables
		if(nodo.hasChildNodes()) {
			NodeList cab = nodo.getChildNodes();
			if( cab.getLength()>=1 )
			{
				for (int temp = 0; temp < cab.getLength(); temp++)
				{
					Node nNode = cab.item(temp);
					if ( nNode.getNodeType()==Node.ELEMENT_NODE )
					{
						if(nNode.getNodeName().equalsIgnoreCase(hijo))
						{
							if(atributo==null || buscarAtributo(nNode, atributo).equals(valorAtributo))
								nodo_return=nNode;
						}
					}
				}
			}
		}
		return nodo_return;
	}
	
	private static ArrayList<Node> getNodosHijo ( Node nodo , String hijo, String atributo, String valorAtributo)
	{
		ArrayList<Node> nodos_return = new ArrayList<Node>();
		//Node nodo_return = null;
		// Analizamos el interior del nodo, buscamos tipos reconocibles y tratables
		NodeList cab = nodo.getChildNodes();
		if( cab.getLength()>=1 )
		{
			for (int temp = 0; temp < cab.getLength(); temp++)
			{
				Node nNode = cab.item(temp);
				if ( nNode.getNodeType()==Node.ELEMENT_NODE )
				{
					if(nNode.getNodeName().equalsIgnoreCase(hijo))
					{
						if(atributo==null || buscarAtributo(nNode, atributo).equals(valorAtributo))
						{
							//nodo_return=nNode;
							nodos_return.add(nNode);
						}
					}
				}
			}
		}
		return nodos_return;
	}
	
	private static String buscarAtributo ( Node nodo , String atributo )
	{
		String auxStr =  "";
		NamedNodeMap atts = nodo.getAttributes();
		for (int temp = 0; temp < atts.getLength(); temp++)
		{
			Node att = atts.item(temp);
			if( att.getNodeName().equalsIgnoreCase(atributo) )
			{
				auxStr = att.getNodeValue();
			}
		}
		return auxStr;
	}
	
	private static String pintarAtributo ( Node nodo , String atributo )
	{
		String auxStr =  "";
		NamedNodeMap atts = nodo.getAttributes();
		for (int temp = 0; temp < atts.getLength(); temp++)
		{
			Node att = atts.item(temp);
			if( att.getNodeName().equalsIgnoreCase(atributo) )
			{
				auxStr = " " + atributo + "=\"" + att.getNodeValue() + "\"";
			}
		}
		return auxStr;
	}
	
	private static String pintarAllAtributos ( Node nodo )
	{
		String auxStr =  "";
		NamedNodeMap atts = nodo.getAttributes();
		for (int temp = 0; temp < atts.getLength(); temp++)
		{
			Node att = atts.item(temp);
			auxStr += " " + att.getNodeName() + "=\"" + att.getNodeValue() + "\"";
		}
		return auxStr;
	}
	
	private static void escribeFicheroErrores(File fileLog, String mensaje){
		BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String data = mensaje;
            // Si el archivo no existe, se crea!
            if (!fileLog.exists()) {
            	fileLog.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(fileLog.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write("\n");
            bw.write(data);
            System.out.println("información agregada!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                            //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	private static String reemplazar ( String origen )
	{
		String auxStr = "";

		HashMap<Character,String> mapa = new HashMap<Character,String>();
		mapa.put('“',"&ldquo;");
		mapa.put('”',"&rdquo;");
		mapa.put('!',"&excl;");
		mapa.put('%',"&percnt;");
		mapa.put('?',"&quest;");
		mapa.put('@',"&commat;");
		mapa.put('[',"&lsqb;");
		mapa.put('\\',"&bsol;");
		mapa.put(']',"&rsqb;");
		mapa.put('^',"&Hat;");
		mapa.put('`',"&grave;");
		mapa.put('{',"&lcub;");
		mapa.put('|',"&verbar;");
		mapa.put('}',"&rcub;");
		mapa.put('¡',"&iexcl;");
		mapa.put('¢',"&cent;");
		mapa.put('£',"&pound;");
		mapa.put('¤',"&curren;");
		mapa.put('¥',"&yen;");
		mapa.put('¦',"&brvbar;");
		mapa.put('§',"&sect;");
		mapa.put('¨',"&Dot;");
		mapa.put('©',"&copy;");
		mapa.put('ª',"&ordf;");
		mapa.put('«',"&laquo;");
		mapa.put('¬',"&not;");
		mapa.put('®',"&reg;");
		mapa.put('¯',"&macr;");
		mapa.put('°',"&deg;");
		mapa.put('±',"&plusmn;");
		mapa.put('²',"&sup2;");
		mapa.put('³',"&sup3;");
		mapa.put('´',"&acute;");
		mapa.put('µ',"&micro;");
		mapa.put('¶',"&para;");
		mapa.put('·',"&middot;");
		mapa.put('¸',"&cedil;");
		mapa.put('¹',"&sup1;");
		mapa.put('º',"&ordm;");
		mapa.put('»',"&raquo;");
		mapa.put('¼',"&frac14;");
		mapa.put('½',"&frac12;");
		mapa.put('¾',"&frac34;");
		mapa.put('¿',"&iquest;");
		mapa.put('À',"&Agrave;");
		mapa.put('Á',"&Aacute;");
		mapa.put('Â',"&Acirc;");
		mapa.put('Ã',"&Atilde;");
		mapa.put('Ä',"&Auml;");
		mapa.put('Å',"&Aring;");
		mapa.put('Æ',"&AElig;");
		mapa.put('Ç',"&Ccedil;");
		mapa.put('È',"&Egrave;");
		mapa.put('É',"&Eacute;");
		mapa.put('Ê',"&Ecirc;");
		mapa.put('Ë',"&Euml;");
		mapa.put('Ì',"&Igrave;");
		mapa.put('Í',"&Iacute;");
		mapa.put('Î',"&Icirc;");
		mapa.put('Ï',"&Iuml;");
		mapa.put('Ð',"&ETH;");
		mapa.put('Ñ',"&Ntilde;");
		mapa.put('Ò',"&Ograve;");
		mapa.put('Ó',"&Oacute;");
		mapa.put('Ô',"&Ocirc;");
		mapa.put('Õ',"&Otilde;");
		mapa.put('Ö',"&Ouml;");
		mapa.put('×',"&times;");
		mapa.put('Ø',"&Oslash;");
		mapa.put('Ù',"&Ugrave;");
		mapa.put('Ú',"&Uacute;");
		mapa.put('Û',"&Ucirc;");
		mapa.put('Ü',"&Uuml;");
		mapa.put('Ý',"&Yacute;");
		mapa.put('Þ',"&THORN;");
		mapa.put('ß',"&szlig;");
		mapa.put('à',"&agrave;");
		mapa.put('á',"&aacute;");
		mapa.put('â',"&acirc;");
		mapa.put('ã',"&atilde;");
		mapa.put('ä',"&auml;");
		mapa.put('å',"&aring;");
		mapa.put('æ',"&aelig;");
		mapa.put('ç',"&ccedil;");
		mapa.put('è',"&egrave;");
		mapa.put('é',"&eacute;");
		mapa.put('ê',"&ecirc;");
		mapa.put('ë',"&euml;");
		mapa.put('ì',"&igrave;");
		mapa.put('í',"&iacute;");
		mapa.put('î',"&icirc;");
		mapa.put('ï',"&iuml;");
		mapa.put('ð',"&eth;");
		mapa.put('ñ',"&ntilde;");
		mapa.put('ò',"&ograve;");
		mapa.put('ó',"&oacute;");
		mapa.put('ô',"&ocirc;");
		mapa.put('õ',"&otilde;");
		mapa.put('ö',"&ouml;");
		mapa.put('÷',"&divide;");
		mapa.put('ø',"&oslash;");
		mapa.put('ù',"&ugrave;");
		mapa.put('ú',"&uacute;");
		mapa.put('û',"&ucirc;");
		mapa.put('ü',"&uuml;");
		mapa.put('ý',"&yacute;");
		mapa.put('þ',"&thorn;");
		mapa.put('ÿ',"&yuml;");
		mapa.put('Ā',"&Amacr;");
		mapa.put('ā',"&amacr;");
		mapa.put('Ă',"&Abreve;");
		mapa.put('ă',"&abreve;");
		mapa.put('Ą',"&Aogon;");
		mapa.put('ą',"&aogon;");
		mapa.put('Ć',"&Cacute;");
		mapa.put('ć',"&cacute;");
		mapa.put('Ĉ',"&Ccirc;");
		mapa.put('ĉ',"&ccirc;");
		mapa.put('Ċ',"&Cdot;");
		mapa.put('ċ',"&cdot;");
		mapa.put('Č',"&Ccaron;");
		mapa.put('č',"&ccaron;");
		mapa.put('Ď',"&Dcaron;");
		mapa.put('ď',"&dcaron;");
		mapa.put('Đ',"&Dstrok;");
		mapa.put('đ',"&dstrok;");
		mapa.put('Ē',"&Emacr;");
		mapa.put('ē',"&emacr;");
		mapa.put('Ė',"&Edot;");
		mapa.put('ė',"&edot;");
		mapa.put('Ę',"&Eogon;");
		mapa.put('ę',"&eogon;");
		mapa.put('Ě',"&Ecaron;");
		mapa.put('ě',"&ecaron;");
		mapa.put('Ĝ',"&Gcirc;");
		mapa.put('ĝ',"&gcirc;");
		mapa.put('Ğ',"&Gbreve;");
		mapa.put('ğ',"&gbreve;");
		mapa.put('Ġ',"&Gdot;");
		mapa.put('ġ',"&gdot;");
		mapa.put('Ģ',"&Gcedil;");
		mapa.put('Ĥ',"&Hcirc;");
		mapa.put('ĥ',"&hcirc;");
		mapa.put('Ħ',"&Hstrok;");
		mapa.put('ħ',"&hstrok;");
		mapa.put('Ĩ',"&Itilde;");
		mapa.put('ĩ',"&itilde;");
		mapa.put('Ī',"&Imacr;");
		mapa.put('ī',"&imacr;");
		mapa.put('Į',"&Iogon;");
		mapa.put('į',"&iogon;");
		mapa.put('İ',"&Idot;");
		mapa.put('ı',"&imath;");
		mapa.put('Ĳ',"&IJlig;");
		mapa.put('ĳ',"&ijlig;");
		mapa.put('Ĵ',"&Jcirc;");
		mapa.put('ĵ',"&jcirc;");
		mapa.put('Ķ',"&Kcedil;");
		mapa.put('ķ',"&kcedil;");
		mapa.put('ĸ',"&kgreen;");
		mapa.put('Ĺ',"&Lacute;");
		mapa.put('ĺ',"&lacute;");
		mapa.put('Ļ',"&Lcedil;");
		mapa.put('ļ',"&lcedil;");
		mapa.put('Ľ',"&Lcaron;");
		mapa.put('ľ',"&lcaron;");
		mapa.put('Ŀ',"&Lmidot;");
		mapa.put('ŀ',"&lmidot;");
		mapa.put('Ł',"&Lstrok;");
		mapa.put('ł',"&lstrok;");
		mapa.put('Ń',"&Nacute;");
		mapa.put('ń',"&nacute;");
		mapa.put('Ņ',"&Ncedil;");
		mapa.put('ņ',"&ncedil;");
		mapa.put('Ň',"&Ncaron;");
		mapa.put('ň',"&ncaron;");
		mapa.put('ŉ',"&napos;");
		mapa.put('Ŋ',"&ENG;");
		mapa.put('ŋ',"&eng;");
		mapa.put('Ō',"&Omacr;");
		mapa.put('ō',"&omacr;");
		mapa.put('Ő',"&Odblac;");
		mapa.put('ő',"&odblac;");
		mapa.put('Œ',"&OElig;");
		mapa.put('œ',"&oelig;");
		mapa.put('Ŕ',"&Racute;");
		mapa.put('ŕ',"&racute;");
		mapa.put('Ŗ',"&Rcedil;");
		mapa.put('ŗ',"&rcedil;");
		mapa.put('Ř',"&Rcaron;");
		mapa.put('ř',"&rcaron;");
		mapa.put('Ś',"&Sacute;");
		mapa.put('ś',"&sacute;");
		mapa.put('Ŝ',"&Scirc;");
		mapa.put('ŝ',"&scirc;");
		mapa.put('Ş',"&Scedil;");
		mapa.put('ş',"&scedil;");
		mapa.put('Š',"&Scaron;");
		mapa.put('š',"&scaron;");
		mapa.put('Ţ',"&Tcedil;");
		mapa.put('ţ',"&tcedil;");
		mapa.put('Ť',"&Tcaron;");
		mapa.put('ť',"&tcaron;");
		mapa.put('Ŧ',"&Tstrok;");
		mapa.put('ŧ',"&tstrok;");
		mapa.put('Ũ',"&Utilde;");
		mapa.put('ũ',"&utilde;");
		mapa.put('Ū',"&Umacr;");
		mapa.put('ū',"&umacr;");
		mapa.put('Ŭ',"&Ubreve;");
		mapa.put('ŭ',"&ubreve;");
		mapa.put('Ů',"&Uring;");
		mapa.put('ů',"&uring;");
		mapa.put('Ű',"&Udblac;");
		mapa.put('ű',"&udblac;");
		mapa.put('Ų',"&Uogon;");
		mapa.put('ų',"&uogon;");
		mapa.put('Ŵ',"&Wcirc;");
		mapa.put('ŵ',"&wcirc;");
		mapa.put('Ŷ',"&Ycirc;");
		mapa.put('ŷ',"&ycirc;");
		mapa.put('Ÿ',"&Yuml;");
		mapa.put('Ź',"&Zacute;");
		mapa.put('ź',"&zacute;");
		mapa.put('Ż',"&Zdot;");
		mapa.put('ż',"&zdot;");
		mapa.put('Ž',"&Zcaron;");
		mapa.put('ž',"&zcaron;");
		mapa.put('ƒ',"&fnof;");
		mapa.put('Ƶ',"&imped;");
		mapa.put('ǵ',"&gacute;");
		mapa.put('ȷ',"&jmath;");
		mapa.put('ˆ',"&circ;");
		mapa.put('ˇ',"&caron;");
		mapa.put('˘',"&breve;");
		mapa.put('˙',"&dot;");
		mapa.put('˚',"&ring;");
		mapa.put('˛',"&ogon;");
		mapa.put('˜',"&tilde;");
		mapa.put('˝',"&dblac;");
		mapa.put('Α',"&Alpha;");
		mapa.put('Β',"&Beta;");
		mapa.put('Γ',"&Gamma;");
		mapa.put('Δ',"&Delta;");
		mapa.put('∆',"&Delta;");
		mapa.put('Ε',"&Epsilon;");
		mapa.put('Ζ',"&Zeta;");
		mapa.put('Η',"&Eta;");
		mapa.put('Θ',"&Theta;");
		mapa.put('Ι',"&Iota;");
		mapa.put('Κ',"&Kappa;");
		mapa.put('Λ',"&Lambda;");
		mapa.put('Μ',"&Mu;");
		mapa.put('Ν',"&Nu;");
		mapa.put('Ξ',"&Xi;");
		mapa.put('Ο',"&Omicron;");
		mapa.put('Π',"&Pi;");
		mapa.put('Ρ',"&Rho;");
		mapa.put('Σ',"&Sigma;");
		mapa.put('Τ',"&Tau;");
		mapa.put('Υ',"&Upsilon;");
		mapa.put('Φ',"&Phi;");
		mapa.put('Χ',"&Chi;");
		mapa.put('Ψ',"&Psi;");
		mapa.put('Ω',"&Omega;");
		mapa.put('α',"&alpha;");
		mapa.put('β',"&beta;");
		mapa.put('γ',"&gamma;");
		mapa.put('δ',"&delta;");
		mapa.put('ε',"&epsiv;");
		mapa.put('ζ',"&zeta;");
		mapa.put('η',"&eta;");
		mapa.put('ƞ',"&eta;");
		mapa.put('θ',"&theta;");
		mapa.put('ι',"&iota;");
		mapa.put('κ',"&kappa;");
		mapa.put('λ',"&lambda;");
		mapa.put('μ',"&mu;");
		mapa.put('ν',"&nu;");
		mapa.put('ξ',"&xi;");
		mapa.put('ο',"&omicron;");
		mapa.put('π',"&pi;");
		mapa.put('ρ',"&rho;");
		mapa.put('ς',"&sigmav;");
		mapa.put('σ',"&sigma;");
		mapa.put('τ',"&tau;");
		mapa.put('υ',"&upsi;");
		mapa.put('φ',"&phi;");
		mapa.put('χ',"&chi;");
		mapa.put('ψ',"&psi;");
		mapa.put('ω',"&omega;");
		mapa.put('ϑ',"&thetav;");
		mapa.put('ϒ',"&Upsi;");
		mapa.put('ϕ',"&straightphi;");
		mapa.put('ϖ',"&piv;");
		mapa.put('Ϝ',"&Gammad;");
		mapa.put('ϝ',"&gammad;");
		mapa.put('ϰ',"&kappav;");
		mapa.put('ϱ',"&rhov;");
		mapa.put('ϵ',"&epsi;");
		mapa.put('϶',"&bepsi;");
		mapa.put('Ё',"&IOcy;");
		mapa.put('Ђ',"&DJcy;");
		mapa.put('Ѓ',"&GJcy;");
		mapa.put('Є',"&Jukcy;");
		mapa.put('Ѕ',"&DScy;");
		mapa.put('І',"&Iukcy;");
		mapa.put('Ї',"&YIcy;");
		mapa.put('Ј',"&Jsercy;");
		mapa.put('Љ',"&LJcy;");
		mapa.put('Њ',"&NJcy;");
		mapa.put('Ћ',"&TSHcy;");
		mapa.put('Ќ',"&KJcy;");
		mapa.put('Ў',"&Ubrcy;");
		mapa.put('Џ',"&DZcy;");
		mapa.put('А',"&Acy;");
		mapa.put('Б',"&Bcy;");
		mapa.put('В',"&Vcy;");
		mapa.put('Г',"&Gcy;");
		mapa.put('Д',"&Dcy;");
		mapa.put('Е',"&IEcy;");
		mapa.put('Ж',"&ZHcy;");
		mapa.put('З',"&Zcy;");
		mapa.put('И',"&Icy;");
		mapa.put('Й',"&Jcy;");
		mapa.put('К',"&Kcy;");
		mapa.put('Л',"&Lcy;");
		mapa.put('М',"&Mcy;");
		mapa.put('Н',"&Ncy;");
		mapa.put('О',"&Ocy;");
		mapa.put('П',"&Pcy;");
		mapa.put('Р',"&Rcy;");
		mapa.put('С',"&Scy;");
		mapa.put('Т',"&Tcy;");
		mapa.put('У',"&Ucy;");
		mapa.put('Ф',"&Fcy;");
		mapa.put('Х',"&KHcy;");
		mapa.put('Ц',"&TScy;");
		mapa.put('Ч',"&CHcy;");
		mapa.put('Ш',"&SHcy;");
		mapa.put('Щ',"&SHCHcy;");
		mapa.put('Ъ',"&HARDcy;");
		mapa.put('Ы',"&Ycy;");
		mapa.put('Ь',"&SOFTcy;");
		mapa.put('Э',"&Ecy;");
		mapa.put('Ю',"&YUcy;");
		mapa.put('Я',"&YAcy;");
		mapa.put('а',"&acy;");
		mapa.put('б',"&bcy;");
		mapa.put('в',"&vcy;");
		mapa.put('г',"&gcy;");
		mapa.put('д',"&dcy;");
		mapa.put('е',"&iecy;");
		mapa.put('ж',"&zhcy;");
		mapa.put('з',"&zcy;");
		mapa.put('и',"&icy;");
		mapa.put('й',"&jcy;");
		mapa.put('к',"&kcy;");
		mapa.put('л',"&lcy;");
		mapa.put('м',"&mcy;");
		mapa.put('н',"&ncy;");
		mapa.put('о',"&ocy;");
		mapa.put('п',"&pcy;");
		mapa.put('р',"&rcy;");
		mapa.put('с',"&scy;");
		mapa.put('т',"&tcy;");
		mapa.put('у',"&ucy;");
		mapa.put('ф',"&fcy;");
		mapa.put('х',"&khcy;");
		mapa.put('ц',"&tscy;");
		mapa.put('ч',"&chcy;");
		mapa.put('ш',"&shcy;");
		mapa.put('щ',"&shchcy;");
		mapa.put('ъ',"&hardcy;");
		mapa.put('ы',"&ycy;");
		mapa.put('ь',"&softcy;");
		mapa.put('э',"&ecy;");
		mapa.put('ю',"&yucy;");
		mapa.put('я',"&yacy;");
		mapa.put('ё',"&iocy;");
		mapa.put('ђ',"&djcy;");
		mapa.put('ѓ',"&gjcy;");
		mapa.put('є',"&jukcy;");
		mapa.put('ѕ',"&dscy;");
		mapa.put('і',"&iukcy;");
		mapa.put('ї',"&yicy;");
		mapa.put('ј',"&jsercy;");
		mapa.put('љ',"&ljcy;");
		mapa.put('њ',"&njcy;");
		mapa.put('ћ',"&tshcy;");
		mapa.put('ќ',"&kjcy;");
		mapa.put('ў',"&ubrcy;");
		mapa.put('џ',"&dzcy;");
		mapa.put('‐',"&hyphen;");
		mapa.put('–',"&ndash;");
		mapa.put('—',"&mdash;");
		mapa.put('―',"&horbar;");
		mapa.put('‖',"&Verbar;");
		mapa.put('‘',"&lsquo;");
		mapa.put('’',"&rsquo;");
		mapa.put('‚',"&lsquor;");
		mapa.put('“',"&ldquo;");
		mapa.put('”',"&rdquo;");
		mapa.put('„',"&ldquor;");
		mapa.put('†',"&dagger;");
		mapa.put('‡',"&Dagger;");
		mapa.put('•',"&bull;");
		mapa.put('‥',"&nldr;");
		mapa.put('…',"&hellip;");
		mapa.put('‰',"&permil;");
		mapa.put('‱',"&pertenk;");
		mapa.put('′',"&prime;");
		mapa.put('″',"&Prime;");
		mapa.put('‴',"&tprime;");
		mapa.put('‵',"&bprime;");
		mapa.put('‹',"&lsaquo;");
		mapa.put('›',"&rsaquo;");
		mapa.put('‾',"&oline;");
		mapa.put('⁁',"&caret;");
		mapa.put('⁃',"&hybull;");
		mapa.put('⁏',"&bsemi;");
		mapa.put('⁗',"&qprime;");
		mapa.put('€',"&euro;");
		mapa.put('ℂ',"&Copf;");
		mapa.put('℅',"&incare;");
		mapa.put('ℊ',"&gscr;");
		mapa.put('ℋ',"&hamilt;");
		mapa.put('ℌ',"&Hfr;");
		mapa.put('ℍ',"&quaternions;");
		mapa.put('ℎ',"&planckh;");
		mapa.put('ℏ',"&planck;");
		mapa.put('ℐ',"&Iscr;");
		mapa.put('ℑ',"&image;");
		mapa.put('ℒ',"&Lscr;");
		mapa.put('ℓ',"&ell;");
		mapa.put('ℕ',"&Nopf;");
		mapa.put('№',"&numero;");
		mapa.put('℗',"&copysr;");
		mapa.put('℘',"&weierp;");
		mapa.put('ℙ',"&Popf;");
		mapa.put('ℚ',"&rationals;");
		mapa.put('ℛ',"&Rscr;");
		mapa.put('ℜ',"&real;");
		mapa.put('ℝ',"&reals;");
		mapa.put('℞',"&rx;");
		mapa.put('™',"&trade;");
		mapa.put('ℤ',"&integers;");
		mapa.put('Ω',"&ohm;");
		mapa.put('℧',"&mho;");
		mapa.put('ℨ',"&Zfr;");
		mapa.put('℩',"&iiota;");
		mapa.put('Å',"&angst;");
		mapa.put('ℬ',"&bernou;");
		mapa.put('ℭ',"&Cfr;");
		mapa.put('ℯ',"&escr;");
		mapa.put('ℰ',"&Escr;");
		mapa.put('ℱ',"&Fscr;");
		mapa.put('ℳ',"&phmmat;");
		mapa.put('ℴ',"&order;");
		mapa.put('ℵ',"&alefsym;");
		mapa.put('ℶ',"&beth;");
		mapa.put('ℷ',"&gimel;");
		mapa.put('ℸ',"&daleth;");
		mapa.put('ⅅ',"&CapitalDifferentialD;");
		mapa.put('ⅆ',"&DifferentialD;");
		mapa.put('ⅇ',"&ExponentialE;");
		mapa.put('ⅈ',"&ImaginaryI;");
		mapa.put('⅓',"&frac13;");
		mapa.put('⅔',"&frac23;");
		mapa.put('⅕',"&frac15;");
		mapa.put('⅖',"&frac25;");
		mapa.put('⅗',"&frac35;");
		mapa.put('⅘',"&frac45;");
		mapa.put('⅙',"&frac16;");
		mapa.put('⅚',"&frac56;");
		mapa.put('⅛',"&frac18;");
		mapa.put('⅜',"&frac38;");
		mapa.put('⅝',"&frac58;");
		mapa.put('⅞',"&frac78;");
		mapa.put('←',"&larr;");
		mapa.put('↑',"&uarr;");
		mapa.put('→',"&rarr;");
		mapa.put('↓',"&darr;");
		mapa.put('↔',"&harr;");
		mapa.put('↕',"&varr;");
		mapa.put('↖',"&nwarr;");
		mapa.put('↗',"&nearr;");
		mapa.put('↘',"&searr;");
		mapa.put('↙',"&swarr;");
		mapa.put('↚',"&nlarr;");
		mapa.put('↛',"&nrarr;");
		mapa.put('↝',"&rarrw;");
		mapa.put('↞',"&Larr;");
		mapa.put('↟',"&Uarr;");
		mapa.put('↠',"&Rarr;");
		mapa.put('↡',"&Darr;");
		mapa.put('↢',"&larrtl;");
		mapa.put('↣',"&rarrtl;");
		mapa.put('↤',"&LeftTeeArrow;");
		mapa.put('↥',"&UpTeeArrow;");
		mapa.put('↦',"&map;");
		mapa.put('↧',"&DownTeeArrow;");
		mapa.put('↩',"&larrhk;");
		mapa.put('↪',"&rarrhk;");
		mapa.put('↫',"&larrlp;");
		mapa.put('↬',"&rarrlp;");
		mapa.put('↭',"&harrw;");
		mapa.put('↮',"&nharr;");
		mapa.put('↰',"&lsh;");
		mapa.put('↱',"&rsh;");
		mapa.put('↲',"&ldsh;");
		mapa.put('↳',"&rdsh;");
		mapa.put('↵',"&crarr;");
		mapa.put('↶',"&cularr;");
		mapa.put('↷',"&curarr;");
		mapa.put('↺',"&olarr;");
		mapa.put('↻',"&orarr;");
		mapa.put('↼',"&lharu;");
		mapa.put('↽',"&lhard;");
		mapa.put('↾',"&uharr;");
		mapa.put('↿',"&uharl;");
		mapa.put('⇀',"&rharu;");
		mapa.put('⇁',"&rhard;");
		mapa.put('⇂',"&dharr;");
		mapa.put('⇃',"&dharl;");
		mapa.put('⇄',"&rlarr;");
		mapa.put('⇅',"&udarr;");
		mapa.put('⇆',"&lrarr;");
		mapa.put('⇇',"&llarr;");
		mapa.put('⇈',"&uuarr;");
		mapa.put('⇉',"&rrarr;");
		mapa.put('⇊',"&ddarr;");
		mapa.put('⇋',"&lrhar;");
		mapa.put('⇌',"&rlhar;");
		mapa.put('⇍',"&nlArr;");
		mapa.put('⇎',"&nhArr;");
		mapa.put('⇏',"&nrArr;");
		mapa.put('⇐',"&lArr;");
		mapa.put('⇑',"&uArr;");
		mapa.put('⇒',"&rArr;");
		mapa.put('⇓',"&dArr;");
		mapa.put('⇔',"&hArr;");
		mapa.put('⇕',"&vArr;");
		mapa.put('⇖',"&nwArr;");
		mapa.put('⇗',"&neArr;");
		mapa.put('⇘',"&seArr;");
		mapa.put('⇙',"&swArr;");
		mapa.put('⇚',"&lAarr;");
		mapa.put('⇛',"&rAarr;");
		mapa.put('⇝',"&zigrarr;");
		mapa.put('⇤',"&larrb;");
		mapa.put('⇥',"&rarrb;");
		mapa.put('⇵',"&duarr;");
		mapa.put('⇽',"&loarr;");
		mapa.put('⇾',"&roarr;");
		mapa.put('⇿',"&hoarr;");
		mapa.put('∀',"&forall;");
		mapa.put('∁',"&comp;");
		mapa.put('∂',"&part;");
		mapa.put('∃',"&exist;");
		mapa.put('∄',"&nexist;");
		mapa.put('∅',"&empty;");
		mapa.put('∇',"&nabla;");
		mapa.put('∈',"&isin;");
		mapa.put('∉',"&notin;");
		mapa.put('∋',"&niv;");
		mapa.put('∌',"&notni;");
		mapa.put('∏',"&prod;");
		mapa.put('∐',"&coprod;");
		mapa.put('∑',"&sum;");
		mapa.put('−',"&minus;");
		mapa.put('∓',"&mnplus;");
		mapa.put('∔',"&plusdo;");
		mapa.put('∖',"&setmn;");
		mapa.put('∗',"&lowast;");
		mapa.put('∘',"&compfn;");
		mapa.put('√',"&radic;");
		mapa.put('∝',"&prop;");
		mapa.put('∞',"&infin;");
		mapa.put('∟',"&angrt;");
		mapa.put('∠',"&ang;");
		mapa.put('∡',"&angmsd;");
		mapa.put('∢',"&angsph;");
		mapa.put('∣',"&mid;");
		mapa.put('∤',"&nmid;");
		mapa.put('∥',"&par;");
		mapa.put('∦',"&npar;");
		mapa.put('∧',"&and;");
		mapa.put('∨',"&or;");
		mapa.put('∩',"&cap;");
		mapa.put('∪',"&cup;");
		mapa.put('∫',"&int;");
		mapa.put('∬',"&Int;");
		mapa.put('∭',"&tint;");
		mapa.put('∮',"&conint;");
		mapa.put('∯',"&Conint;");
		mapa.put('∰',"&Cconint;");
		mapa.put('∱',"&cwint;");
		mapa.put('∲',"&cwconint;");
		mapa.put('∳',"&awconint;");
		mapa.put('∴',"&there4;");
		mapa.put('∵',"&becaus;");
		mapa.put('∶',"&ratio;");
		mapa.put('∷',"&Colon;");
		mapa.put('∸',"&minusd;");
		mapa.put('∺',"&mDDot;");
		mapa.put('∻',"&homtht;");
		mapa.put('∼',"&sim;");
		mapa.put('∽',"&bsim;");
		mapa.put('∾',"&ac;");
		mapa.put('∿',"&acd;");
		mapa.put('≀',"&wreath;");
		mapa.put('≁',"&nsim;");
		mapa.put('≂',"&esim;");
		mapa.put('≃',"&sime;");
		mapa.put('≄',"&nsime;");
		mapa.put('≅',"&cong;");
		mapa.put('≆',"&simne;");
		mapa.put('≇',"&ncong;");
		mapa.put('≈',"&asymp;");
		mapa.put('≉',"&nap;");
		mapa.put('≊',"&ape;");
		mapa.put('≋',"&apid;");
		mapa.put('≌',"&bcong;");
		mapa.put('≍',"&asympeq;");
		mapa.put('≎',"&bump;");
		mapa.put('≏',"&bumpe;");
		mapa.put('≐',"&esdot;");
		mapa.put('≑',"&eDot;");
		mapa.put('≒',"&efDot;");
		mapa.put('≓',"&erDot;");
		mapa.put('≔',"&colone;");
		mapa.put('≕',"&ecolon;");
		mapa.put('≖',"&ecir;");
		mapa.put('≗',"&cire;");
		mapa.put('≙',"&wedgeq;");
		mapa.put('≚',"&veeeq;");
		mapa.put('≜',"&trie;");
		mapa.put('≟',"&equest;");
		mapa.put('≠',"&ne;");
		mapa.put('≡',"&equiv;");
		mapa.put('≢',"&nequiv;");
		mapa.put('≤',"&le;");
		mapa.put('≥',"&ge;");
		mapa.put('≦',"&lE;");
		mapa.put('≧',"&gE;");
		mapa.put('≨',"&lnE;");
		mapa.put('≩',"&gnE;");
		mapa.put('≪',"&Lt;");
		mapa.put('≫',"&Gt;");
		mapa.put('≬',"&twixt;");
		mapa.put('≭',"&NotCupCap;");
		mapa.put('≮',"&nlt;");
		mapa.put('≯',"&ngt;");
		mapa.put('≰',"&nle;");
		mapa.put('≱',"&nge;");
		mapa.put('≲',"&lsim;");
		mapa.put('≳',"&gsim;");
		mapa.put('≴',"&nlsim;");
		mapa.put('≵',"&ngsim;");
		mapa.put('≶',"&lg;");
		mapa.put('≷',"&gl;");
		mapa.put('≸',"&ntlg;");
		mapa.put('≹',"&ntgl;");
		mapa.put('≺',"&pr;");
		mapa.put('≻',"&sc;");
		mapa.put('≼',"&prcue;");
		mapa.put('≽',"&sccue;");
		mapa.put('≾',"&prsim;");
		mapa.put('≿',"&scsim;");
		mapa.put('⊀',"&npr;");
		mapa.put('⊁',"&nsc;");
		mapa.put('⊂',"&sub;");
		mapa.put('⊃',"&sup;");
		mapa.put('⊄',"&nsub;");
		mapa.put('⊅',"&nsup;");
		mapa.put('⊆',"&sube;");
		mapa.put('⊇',"&supe;");
		mapa.put('⊈',"&nsube;");
		mapa.put('⊉',"&nsupe;");
		mapa.put('⊊',"&subne;");
		mapa.put('⊋',"&supne;");
		mapa.put('⊍',"&cupdot;");
		mapa.put('⊎',"&uplus;");
		mapa.put('⊏',"&sqsub;");
		mapa.put('⊐',"&sqsup;");
		mapa.put('⊑',"&sqsube;");
		mapa.put('⊒',"&sqsupe;");
		mapa.put('⊓',"&sqcap;");
		mapa.put('⊔',"&sqcup;");
		mapa.put('⊕',"&oplus;");
		mapa.put('⊖',"&ominus;");
		mapa.put('⊗',"&otimes;");
		mapa.put('⊘',"&osol;");
		mapa.put('⊙',"&odot;");
		mapa.put('⊚',"&ocir;");
		mapa.put('⊛',"&oast;");
		mapa.put('⊝',"&odash;");
		mapa.put('⊞',"&plusb;");
		mapa.put('⊟',"&minusb;");
		mapa.put('⊠',"&timesb;");
		mapa.put('⊡',"&sdotb;");
		mapa.put('⊢',"&vdash;");
		mapa.put('⊣',"&dashv;");
		mapa.put('⊤',"&top;");
		mapa.put('⊥',"&bottom;");
		mapa.put('⊧',"&models;");
		mapa.put('⊨',"&vDash;");
		mapa.put('⊩',"&Vdash;");
		mapa.put('⊪',"&Vvdash;");
		mapa.put('⊫',"&VDash;");
		mapa.put('⊬',"&nvdash;");
		mapa.put('⊭',"&nvDash;");
		mapa.put('⊮',"&nVdash;");
		mapa.put('⊯',"&nVDash;");
		mapa.put('⊰',"&prurel;");
		mapa.put('⊲',"&vltri;");
		mapa.put('⊳',"&vrtri;");
		mapa.put('⊴',"&ltrie;");
		mapa.put('⊵',"&rtrie;");
		mapa.put('⊶',"&origof;");
		mapa.put('⊷',"&imof;");
		mapa.put('⊸',"&mumap;");
		mapa.put('⊹',"&hercon;");
		mapa.put('⊺',"&intcal;");
		mapa.put('⊻',"&veebar;");
		mapa.put('⊽',"&barvee;");
		mapa.put('⊾',"&angrtvb;");
		mapa.put('⊿',"&lrtri;");
		mapa.put('⋀',"&xwedge;");
		mapa.put('⋁',"&xvee;");
		mapa.put('⋂',"&xcap;");
		mapa.put('⋃',"&xcup;");
		mapa.put('⋄',"&diam;");
		mapa.put('⋅',"&sdot;");
		mapa.put('⋆',"&sstarf;");
		mapa.put('⋇',"&divonx;");
		mapa.put('⋈',"&bowtie;");
		mapa.put('⋉',"&ltimes;");
		mapa.put('⋊',"&rtimes;");
		mapa.put('⋋',"&lthree;");
		mapa.put('⋌',"&rthree;");
		mapa.put('⋍',"&bsime;");
		mapa.put('⋎',"&cuvee;");
		mapa.put('⋏',"&cuwed;");
		mapa.put('⋐',"&Sub;");
		mapa.put('⋑',"&Sup;");
		mapa.put('⋒',"&Cap;");
		mapa.put('⋓',"&Cup;");
		mapa.put('⋔',"&fork;");
		mapa.put('⋕',"&epar;");
		mapa.put('⋖',"&ltdot;");
		mapa.put('⋗',"&gtdot;");
		mapa.put('⋘',"&Ll;");
		mapa.put('⋙',"&Gg;");
		mapa.put('⋚',"&leg;");
		mapa.put('⋛',"&gel;");
		mapa.put('⋞',"&cuepr;");
		mapa.put('⋟',"&cuesc;");
		mapa.put('⋠',"&nprcue;");
		mapa.put('⋡',"&nsccue;");
		mapa.put('⋢',"&nsqsube;");
		mapa.put('⋣',"&nsqsupe;");
		mapa.put('⋦',"&lnsim;");
		mapa.put('⋧',"&gnsim;");
		mapa.put('⋨',"&prnsim;");
		mapa.put('⋩',"&scnsim;");
		mapa.put('⋪',"&nltri;");
		mapa.put('⋫',"&nrtri;");
		mapa.put('⋬',"&nltrie;");
		mapa.put('⋭',"&nrtrie;");
		mapa.put('⋮',"&vellip;");
		mapa.put('⋯',"&ctdot;");
		mapa.put('⋰',"&utdot;");
		mapa.put('⋱',"&dtdot;");
		mapa.put('⋲',"&disin;");
		mapa.put('⋳',"&isinsv;");
		mapa.put('⋴',"&isins;");
		mapa.put('⋵',"&isindot;");
		mapa.put('⋶',"&notinvc;");
		mapa.put('⋷',"&notinvb;");
		mapa.put('⋹',"&isinE;");
		mapa.put('⋺',"&nisd;");
		mapa.put('⋻',"&xnis;");
		mapa.put('⋼',"&nis;");
		mapa.put('⋽',"&notnivc;");
		mapa.put('⋾',"&notnivb;");
		mapa.put('⌅',"&barwed;");
		mapa.put('⌆',"&Barwed;");
		mapa.put('⌈',"&lceil;");
		mapa.put('⌉',"&rceil;");
		mapa.put('⌊',"&lfloor;");
		mapa.put('⌋',"&rfloor;");
		mapa.put('⌌',"&drcrop;");
		mapa.put('⌍',"&dlcrop;");
		mapa.put('⌎',"&urcrop;");
		mapa.put('⌏',"&ulcrop;");
		mapa.put('⌐',"&bnot;");
		mapa.put('⌒',"&profline;");
		mapa.put('⌓',"&profsurf;");
		mapa.put('⌕',"&telrec;");
		mapa.put('⌖',"&target;");
		mapa.put('⌜',"&ulcorn;");
		mapa.put('⌝',"&urcorn;");
		mapa.put('⌞',"&dlcorn;");
		mapa.put('⌟',"&drcorn;");
		mapa.put('⌢',"&frown;");
		mapa.put('⌣',"&smile;");
		mapa.put('⌭',"&cylcty;");
		mapa.put('⌮',"&profalar;");
		mapa.put('⌶',"&topbot;");
		mapa.put('⌽',"&ovbar;");
		mapa.put('⌿',"&solbar;");
		mapa.put('⍼',"&angzarr;");
		mapa.put('⎰',"&lmoust;");
		mapa.put('⎱',"&rmoust;");
		mapa.put('⎴',"&tbrk;");
		mapa.put('⎵',"&bbrk;");
		mapa.put('⎶',"&bbrktbrk;");
		mapa.put('⏜',"&OverParenthesis;");
		mapa.put('⏝',"&UnderParenthesis;");
		mapa.put('⏞',"&OverBrace;");
		mapa.put('⏟',"&UnderBrace;");
		mapa.put('⏢',"&trpezium;");
		mapa.put('⏧',"&elinters;");
		mapa.put('␣',"&blank;");
		mapa.put('Ⓢ',"&oS;");
		mapa.put('─',"&boxh;");
		mapa.put('│',"&boxv;");
		mapa.put('┌',"&boxdr;");
		mapa.put('┐',"&boxdl;");
		mapa.put('└',"&boxur;");
		mapa.put('┘',"&boxul;");
		mapa.put('├',"&boxvr;");
		mapa.put('┤',"&boxvl;");
		mapa.put('┬',"&boxhd;");
		mapa.put('┴',"&boxhu;");
		mapa.put('┼',"&boxvh;");
		mapa.put('═',"&boxH;");
		mapa.put('║',"&boxV;");
		mapa.put('╒',"&boxdR;");
		mapa.put('╓',"&boxDr;");
		mapa.put('╔',"&boxDR;");
		mapa.put('╕',"&boxdL;");
		mapa.put('╖',"&boxDl;");
		mapa.put('╗',"&boxDL;");
		mapa.put('╘',"&boxuR;");
		mapa.put('╙',"&boxUr;");
		mapa.put('╚',"&boxUR;");
		mapa.put('╛',"&boxuL;");
		mapa.put('╜',"&boxUl;");
		mapa.put('╝',"&boxUL;");
		mapa.put('╞',"&boxvR;");
		mapa.put('╟',"&boxVr;");
		mapa.put('╠',"&boxVR;");
		mapa.put('╡',"&boxvL;");
		mapa.put('╢',"&boxVl;");
		mapa.put('╣',"&boxVL;");
		mapa.put('╤',"&boxHd;");
		mapa.put('╥',"&boxhD;");
		mapa.put('╦',"&boxHD;");
		mapa.put('╧',"&boxHu;");
		mapa.put('╨',"&boxhU;");
		mapa.put('╩',"&boxHU;");
		mapa.put('╪',"&boxvH;");
		mapa.put('╫',"&boxVh;");
		mapa.put('╬',"&boxVH;");
		mapa.put('▀',"&uhblk;");
		mapa.put('▄',"&lhblk;");
		mapa.put('█',"&block;");
		mapa.put('░',"&blk14;");
		mapa.put('▒',"&blk12;");
		mapa.put('▓',"&blk34;");
		mapa.put('□',"&squ;");
		mapa.put('▪',"&squf;");
		mapa.put('▫',"&EmptyVerySmallSquare;");
		mapa.put('▭',"&rect;");
		mapa.put('▮',"&marker;");
		mapa.put('▱',"&fltns;");
		mapa.put('△',"&xutri;");
		mapa.put('▴',"&utrif;");
		mapa.put('▵',"&utri;");
		mapa.put('▸',"&rtrif;");
		mapa.put('▹',"&rtri;");
		mapa.put('▽',"&xdtri;");
		mapa.put('▾',"&dtrif;");
		mapa.put('▿',"&dtri;");
		mapa.put('◂',"&ltrif;");
		mapa.put('◃',"&ltri;");
		mapa.put('◊',"&loz;");
		mapa.put('○',"&cir;");
		mapa.put('◬',"&tridot;");
		mapa.put('◯',"&xcirc;");
		mapa.put('◸',"&ultri;");
		mapa.put('◹',"&urtri;");
		mapa.put('◺',"&lltri;");
		mapa.put('◻',"&EmptySmallSquare;");
		mapa.put('◼',"&FilledSmallSquare;");
		mapa.put('★',"&starf;");
		mapa.put('☆',"&star;");
		mapa.put('☎',"&phone;");
		mapa.put('♀',"&female;");
		mapa.put('♂',"&male;");
		mapa.put('♠',"&spades;");
		mapa.put('♣',"&clubs;");
		mapa.put('♥',"&hearts;");
		mapa.put('♦',"&diams;");
		mapa.put('♪',"&sung;");
		mapa.put('♭',"&flat;");
		mapa.put('♮',"&natur;");
		mapa.put('♯',"&sharp;");
		mapa.put('✓',"&check;");
		mapa.put('✗',"&cross;");
		mapa.put('✠',"&malt;");
		mapa.put('✶',"&sext;");
		mapa.put('❘',"&VerticalSeparator;");
		mapa.put('❲',"&lbbrk;");
		mapa.put('❳',"&rbbrk;");
		mapa.put('⟦',"&lobrk;");
		mapa.put('⟧',"&robrk;");
		mapa.put('⟨',"&lang;");
		mapa.put('⟩',"&rang;");
		mapa.put('⟪',"&Lang;");
		mapa.put('⟫',"&Rang;");
		mapa.put('⟬',"&loang;");
		mapa.put('⟭',"&roang;");
		mapa.put('⟵',"&xlarr;");
		mapa.put('⟶',"&xrarr;");
		mapa.put('⟷',"&xharr;");
		mapa.put('⟸',"&xlArr;");
		mapa.put('⟹',"&xrArr;");
		mapa.put('⟺',"&xhArr;");
		mapa.put('⟼',"&xmap;");
		mapa.put('⟿',"&dzigrarr;");
		mapa.put('⤂',"&nvlArr;");
		mapa.put('⤃',"&nvrArr;");
		mapa.put('⤄',"&nvHarr;");
		mapa.put('⤅',"&Map;");
		mapa.put('⤌',"&lbarr;");
		mapa.put('⤍',"&rbarr;");
		mapa.put('⤎',"&lBarr;");
		mapa.put('⤏',"&rBarr;");
		mapa.put('⤐',"&RBarr;");
		mapa.put('⤑',"&DDotrahd;");
		mapa.put('⤒',"&UpArrowBar;");
		mapa.put('⤓',"&DownArrowBar;");
		mapa.put('⤖',"&Rarrtl;");
		mapa.put('⤙',"&latail;");
		mapa.put('⤚',"&ratail;");
		mapa.put('⤛',"&lAtail;");
		mapa.put('⤜',"&rAtail;");
		mapa.put('⤝',"&larrfs;");
		mapa.put('⤞',"&rarrfs;");
		mapa.put('⤟',"&larrbfs;");
		mapa.put('⤠',"&rarrbfs;");
		mapa.put('⤣',"&nwarhk;");
		mapa.put('⤤',"&nearhk;");
		mapa.put('⤥',"&searhk;");
		mapa.put('⤦',"&swarhk;");
		mapa.put('⤧',"&nwnear;");
		mapa.put('⤨',"&nesear;");
		mapa.put('⤩',"&seswar;");
		mapa.put('⤪',"&swnwar;");
		mapa.put('⤳',"&rarrc;");
		mapa.put('⤵',"&cudarrr;");
		mapa.put('⤶',"&ldca;");
		mapa.put('⤷',"&rdca;");
		mapa.put('⤸',"&cudarrl;");
		mapa.put('⤹',"&larrpl;");
		mapa.put('⤼',"&curarrm;");
		mapa.put('⤽',"&cularrp;");
		mapa.put('⥅',"&rarrpl;");
		mapa.put('⥈',"&harrcir;");
		mapa.put('⥉',"&Uarrocir;");
		mapa.put('⥊',"&lurdshar;");
		mapa.put('⥋',"&ldrushar;");
		mapa.put('⥎',"&LeftRightVector;");
		mapa.put('⥏',"&RightUpDownVector;");
		mapa.put('⥐',"&DownLeftRightVector;");
		mapa.put('⥑',"&LeftUpDownVector;");
		mapa.put('⥒',"&LeftVectorBar;");
		mapa.put('⥓',"&RightVectorBar;");
		mapa.put('⥔',"&RightUpVectorBar;");
		mapa.put('⥕',"&RightDownVectorBar;");
		mapa.put('⥖',"&DownLeftVectorBar;");
		mapa.put('⥗',"&DownRightVectorBar;");
		mapa.put('⥘',"&LeftUpVectorBar;");
		mapa.put('⥙',"&LeftDownVectorBar;");
		mapa.put('⥚',"&LeftTeeVector;");
		mapa.put('⥛',"&RightTeeVector;");
		mapa.put('⥜',"&RightUpTeeVector;");
		mapa.put('⥝',"&RightDownTeeVector;");
		mapa.put('⥞',"&DownLeftTeeVector;");
		mapa.put('⥟',"&DownRightTeeVector;");
		mapa.put('⥠',"&LeftUpTeeVector;");
		mapa.put('⥡',"&LeftDownTeeVector;");
		mapa.put('⥢',"&lHar;");
		mapa.put('⥣',"&uHar;");
		mapa.put('⥤',"&rHar;");
		mapa.put('⥥',"&dHar;");
		mapa.put('⥦',"&luruhar;");
		mapa.put('⥧',"&ldrdhar;");
		mapa.put('⥨',"&ruluhar;");
		mapa.put('⥩',"&rdldhar;");
		mapa.put('⥪',"&lharul;");
		mapa.put('⥫',"&llhard;");
		mapa.put('⥬',"&rharul;");
		mapa.put('⥭',"&lrhard;");
		mapa.put('⥮',"&udhar;");
		mapa.put('⥯',"&duhar;");
		mapa.put('⥰',"&RoundImplies;");
		mapa.put('⥱',"&erarr;");
		mapa.put('⥲',"&simrarr;");
		mapa.put('⥳',"&larrsim;");
		mapa.put('⥴',"&rarrsim;");
		mapa.put('⥵',"&rarrap;");
		mapa.put('⥶',"&ltlarr;");
		mapa.put('⥸',"&gtrarr;");
		mapa.put('⥹',"&subrarr;");
		mapa.put('⥻',"&suplarr;");
		mapa.put('⥼',"&lfisht;");
		mapa.put('⥽',"&rfisht;");
		mapa.put('⥾',"&ufisht;");
		mapa.put('⥿',"&dfisht;");
		mapa.put('⦅',"&lopar;");
		mapa.put('⦆',"&ropar;");
		mapa.put('⦋',"&lbrke;");
		mapa.put('⦌',"&rbrke;");
		mapa.put('⦍',"&lbrkslu;");
		mapa.put('⦎',"&rbrksld;");
		mapa.put('⦏',"&lbrksld;");
		mapa.put('⦐',"&rbrkslu;");
		mapa.put('⦑',"&langd;");
		mapa.put('⦒',"&rangd;");
		mapa.put('⦓',"&lparlt;");
		mapa.put('⦔',"&rpargt;");
		mapa.put('⦕',"&gtlPar;");
		mapa.put('⦖',"&ltrPar;");
		mapa.put('⦚',"&vzigzag;");
		mapa.put('⦜',"&vangrt;");
		mapa.put('⦝',"&angrtvbd;");
		mapa.put('⦤',"&ange;");
		mapa.put('⦥',"&range;");
		mapa.put('⦦',"&dwangle;");
		mapa.put('⦧',"&uwangle;");
		mapa.put('⦨',"&angmsdaa;");
		mapa.put('⦩',"&angmsdab;");
		mapa.put('⦪',"&angmsdac;");
		mapa.put('⦫',"&angmsdad;");
		mapa.put('⦬',"&angmsdae;");
		mapa.put('⦭',"&angmsdaf;");
		mapa.put('⦮',"&angmsdag;");
		mapa.put('⦯',"&angmsdah;");
		mapa.put('⦰',"&bemptyv;");
		mapa.put('⦱',"&demptyv;");
		mapa.put('⦲',"&cemptyv;");
		mapa.put('⦳',"&raemptyv;");
		mapa.put('⦴',"&laemptyv;");
		mapa.put('⦵',"&ohbar;");
		mapa.put('⦶',"&omid;");
		mapa.put('⦷',"&opar;");
		mapa.put('⦹',"&operp;");
		mapa.put('⦻',"&olcross;");
		mapa.put('⦼',"&odsold;");
		mapa.put('⦾',"&olcir;");
		mapa.put('⦿',"&ofcir;");
		mapa.put('⧀',"&olt;");
		mapa.put('⧁',"&ogt;");
		mapa.put('⧂',"&cirscir;");
		mapa.put('⧃',"&cirE;");
		mapa.put('⧄',"&solb;");
		mapa.put('⧅',"&bsolb;");
		mapa.put('⧉',"&boxbox;");
		mapa.put('⧍',"&trisb;");
		mapa.put('⧎',"&rtriltri;");
		mapa.put('⧏',"&LeftTriangleBar;");
		mapa.put('⧐',"&RightTriangleBar;");
		mapa.put('⧚',"&race;");
		mapa.put('⧜',"&iinfin;");
		mapa.put('⧝',"&infintie;");
		mapa.put('⧞',"&nvinfin;");
		mapa.put('⧣',"&eparsl;");
		mapa.put('⧤',"&smeparsl;");
		mapa.put('⧥',"&eqvparsl;");
		mapa.put('⧫',"&lozf;");
		mapa.put('⧴',"&RuleDelayed;");
		mapa.put('⧶',"&dsol;");
		mapa.put('⨀',"&xodot;");
		mapa.put('⨁',"&xoplus;");
		mapa.put('⨂',"&xotime;");
		mapa.put('⨄',"&xuplus;");
		mapa.put('⨆',"&xsqcup;");
		mapa.put('⨌',"&qint;");
		mapa.put('⨍',"&fpartint;");
		mapa.put('⨐',"&cirfnint;");
		mapa.put('⨑',"&awint;");
		mapa.put('⨒',"&rppolint;");
		mapa.put('⨓',"&scpolint;");
		mapa.put('⨔',"&npolint;");
		mapa.put('⨕',"&pointint;");
		mapa.put('⨖',"&quatint;");
		mapa.put('⨗',"&intlarhk;");
		mapa.put('⨢',"&pluscir;");
		mapa.put('⨣',"&plusacir;");
		mapa.put('⨤',"&simplus;");
		mapa.put('⨥',"&plusdu;");
		mapa.put('⨦',"&plussim;");
		mapa.put('⨧',"&plustwo;");
		mapa.put('⨩',"&mcomma;");
		mapa.put('⨪',"&minusdu;");
		mapa.put('⨭',"&loplus;");
		mapa.put('⨮',"&roplus;");
		mapa.put('⨯',"&Cross;");
		mapa.put('⨰',"&timesd;");
		mapa.put('⨱',"&timesbar;");
		mapa.put('⨳',"&smashp;");
		mapa.put('⨴',"&lotimes;");
		mapa.put('⨵',"&rotimes;");
		mapa.put('⨶',"&otimesas;");
		mapa.put('⨷',"&Otimes;");
		mapa.put('⨸',"&odiv;");
		mapa.put('⨹',"&triplus;");
		mapa.put('⨺',"&triminus;");
		mapa.put('⨻',"&tritime;");
		mapa.put('⨼',"&iprod;");
		mapa.put('⨿',"&amalg;");
		mapa.put('⩀',"&capdot;");
		mapa.put('⩂',"&ncup;");
		mapa.put('⩃',"&ncap;");
		mapa.put('⩄',"&capand;");
		mapa.put('⩅',"&cupor;");
		mapa.put('⩆',"&cupcap;");
		mapa.put('⩇',"&capcup;");
		mapa.put('⩈',"&cupbrcap;");
		mapa.put('⩉',"&capbrcup;");
		mapa.put('⩊',"&cupcup;");
		mapa.put('⩋',"&capcap;");
		mapa.put('⩌',"&ccups;");
		mapa.put('⩍',"&ccaps;");
		mapa.put('⩐',"&ccupssm;");
		mapa.put('⩓',"&And;");
		mapa.put('⩔',"&Or;");
		mapa.put('⩕',"&andand;");
		mapa.put('⩖',"&oror;");
		mapa.put('⩗',"&orslope;");
		mapa.put('⩘',"&andslope;");
		mapa.put('⩚',"&andv;");
		mapa.put('⩛',"&orv;");
		mapa.put('⩜',"&andd;");
		mapa.put('⩝',"&ord;");
		mapa.put('⩟',"&wedbar;");
		mapa.put('⩦',"&sdote;");
		mapa.put('⩪',"&simdot;");
		mapa.put('⩭',"&congdot;");
		mapa.put('⩮',"&easter;");
		mapa.put('⩯',"&apacir;");
		mapa.put('⩰',"&apE;");
		mapa.put('⩱',"&eplus;");
		mapa.put('⩲',"&pluse;");
		mapa.put('⩳',"&Esim;");
		mapa.put('⩴',"&Colone;");
		mapa.put('⩵',"&Equal;");
		mapa.put('⩷',"&eDDot;");
		mapa.put('⩸',"&equivDD;");
		mapa.put('⩹',"&ltcir;");
		mapa.put('⩺',"&gtcir;");
		mapa.put('⩻',"&ltquest;");
		mapa.put('⩼',"&gtquest;");
		mapa.put('⩽',"&les;");
		mapa.put('⩾',"&ges;");
		mapa.put('⩿',"&lesdot;");
		mapa.put('⪀',"&gesdot;");
		mapa.put('⪁',"&lesdoto;");
		mapa.put('⪂',"&gesdoto;");
		mapa.put('⪃',"&lesdotor;");
		mapa.put('⪄',"&gesdotol;");
		mapa.put('⪅',"&lap;");
		mapa.put('⪆',"&gap;");
		mapa.put('⪇',"&lne;");
		mapa.put('⪈',"&gne;");
		mapa.put('⪉',"&lnap;");
		mapa.put('⪊',"&gnap;");
		mapa.put('⪋',"&lEg;");
		mapa.put('⪌',"&gEl;");
		mapa.put('⪍',"&lsime;");
		mapa.put('⪎',"&gsime;");
		mapa.put('⪏',"&lsimg;");
		mapa.put('⪐',"&gsiml;");
		mapa.put('⪑',"&lgE;");
		mapa.put('⪒',"&glE;");
		mapa.put('⪓',"&lesges;");
		mapa.put('⪔',"&gesles;");
		mapa.put('⪕',"&els;");
		mapa.put('⪖',"&egs;");
		mapa.put('⪗',"&elsdot;");
		mapa.put('⪘',"&egsdot;");
		mapa.put('⪙',"&el;");
		mapa.put('⪚',"&eg;");
		mapa.put('⪝',"&siml;");
		mapa.put('⪞',"&simg;");
		mapa.put('⪟',"&simlE;");
		mapa.put('⪠',"&simgE;");
		mapa.put('⪡',"&LessLess;");
		mapa.put('⪢',"&GreaterGreater;");
		mapa.put('⪤',"&glj;");
		mapa.put('⪥',"&gla;");
		mapa.put('⪦',"&ltcc;");
		mapa.put('⪧',"&gtcc;");
		mapa.put('⪨',"&lescc;");
		mapa.put('⪩',"&gescc;");
		mapa.put('⪪',"&smt;");
		mapa.put('⪫',"&lat;");
		mapa.put('⪬',"&smte;");
		mapa.put('⪭',"&late;");
		mapa.put('⪮',"&bumpE;");
		mapa.put('⪯',"&pre;");
		mapa.put('⪰',"&sce;");
		mapa.put('⪳',"&prE;");
		mapa.put('⪴',"&scE;");
		mapa.put('⪵',"&prnE;");
		mapa.put('⪶',"&scnE;");
		mapa.put('⪷',"&prap;");
		mapa.put('⪸',"&scap;");
		mapa.put('⪹',"&prnap;");
		mapa.put('⪺',"&scnap;");
		mapa.put('⪻',"&Pr;");
		mapa.put('⪼',"&Sc;");
		mapa.put('⪽',"&subdot;");
		mapa.put('⪾',"&supdot;");
		mapa.put('⪿',"&subplus;");
		mapa.put('⫀',"&supplus;");
		mapa.put('⫁',"&submult;");
		mapa.put('⫂',"&supmult;");
		mapa.put('⫃',"&subedot;");
		mapa.put('⫄',"&supedot;");
		mapa.put('⫅',"&subE;");
		mapa.put('⫆',"&supE;");
		mapa.put('⫇',"&subsim;");
		mapa.put('⫈',"&supsim;");
		mapa.put('⫋',"&subnE;");
		mapa.put('⫌',"&supnE;");
		mapa.put('⫏',"&csub;");
		mapa.put('⫐',"&csup;");
		mapa.put('⫑',"&csube;");
		mapa.put('⫒',"&csupe;");
		mapa.put('⫓',"&subsup;");
		mapa.put('⫔',"&supsub;");
		mapa.put('⫕',"&subsub;");
		mapa.put('⫖',"&supsup;");
		mapa.put('⫗',"&suphsub;");
		mapa.put('⫘',"&supdsub;");
		mapa.put('⫙',"&forkv;");
		mapa.put('⫚',"&topfork;");
		mapa.put('⫛',"&mlcp;");
		mapa.put('⫤',"&Dashv;");
		mapa.put('⫦',"&Vdashl;");
		mapa.put('⫧',"&Barv;");
		mapa.put('⫨',"&vBar;");
		mapa.put('⫩',"&vBarv;");
		mapa.put('⫫',"&Vbar;");
		mapa.put('⫬',"&Not;");
		mapa.put('⫭',"&bNot;");
		mapa.put('⫮',"&rnmid;");
		mapa.put('⫯',"&cirmid;");
		mapa.put('⫰',"&midcir;");
		mapa.put('⫱',"&topcir;");
		mapa.put('⫲',"&nhpar;");
		mapa.put('⫳',"&parsim;");
		mapa.put('⫽',"&parsl;");
		mapa.put('ﬀ',"&fflig;");
		mapa.put('ﬁ',"&filig;");
		mapa.put('ﬂ',"&fllig;");
		mapa.put('ﬃ',"&ffilig;");
		mapa.put('ﬄ',"&ffllig;");
		//simbolos que nos pasa signo
		//mapa.put('Ø',"&Oslash;");
		//mapa.put('æ',"&aelig;");
		//mapa.put('ş',"&scedil;");
		//mapa.put('χ',"&chi;");
		//mapa.put('Χ',"&Chi;");
		//mapa.put('η',"&eta;");
		//mapa.put('α',"&alpha;");
		//mapa.put('β',"&beta;");
		//mapa.put('μ',"&mu;");
		//mapa.put('λ',"&lambda;");
		//mapa.put('Λ',"&Lambda;");
		//mapa.put('ω',"&omega;");
		//mapa.put('ε',"&epsilon;");
		//mapa.put('ϒ',"&Upsi;");
		//mapa.put('ς',"&sigmav;");
		//mapa.put('σ',"&sigma;");
		//mapa.put('κ',"&kappa;");
		//mapa.put('δ',"&delta;");
		//mapa.put('∆',"&Delta;");
		//mapa.put('ζ',"&zeta;");
		//mapa.put('ρ',"&rho;");

		
		
		try {
			/*int iterador=0;
			StringBuffer cadena = new StringBuffer();
			FileReader fr_chars = new FileReader("hashcaracteres.txt");
			BufferedReader bf_chars = new BufferedReader(fr_chars);
			String sCadena="";
			while ((sCadena = bf_chars.readLine())!=null) 
			{
				System.out.println(iterador + " " + sCadena);
				iterador++;
				sCadena = Charset.forName("UTF-8").decode(ByteBuffer.wrap(sCadena.getBytes())).toString();
				String prim = sCadena.substring(0, 1);
				System.out.println(prim.toCharArray()[0]);
				mapa.put(prim.toCharArray()[0],sCadena.substring(sCadena.indexOf("&")));
				if(sCadena.charAt(0)=='”') {
					System.out.println("lo leiiiiii "+sCadena);
				}
			}
			//mapa.put('”',"&rdquo;");*/
			System.out.println(mapa.size());
			//System.out.println(iterador);
			//bf_chars.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		for ( int i = 0; i<origen.length(); i++)
		{
			char auxChar = origen.charAt(i);
			
			String auxValor2 = mapa.get(auxChar);
			if(auxChar=='”') {
				auxValor2="&rdquo;";
				System.out.println("aquiiiii estaaaaaa" + auxChar + ":" + auxChar + ":" + auxValor2);
			}
			if( auxValor2!=null )
			{
				System.out.println("==========================> " + auxChar + ":" + auxChar + ":" + auxValor2);
				auxStr += auxValor2;
			}
			else
			{
				auxStr += auxChar;
			}
		}
		
		return auxStr;
	}
	
	public static String buscarTextoPagina(String url, String texto, File ficheroLog) {
		String error="";
		String url_original=url;
		String cadena_redirect="redirect";
		String cadena_redirect1=" moved";
		String cadena_redirect2="301 ";
		String url_redirect="";
		String url_redirect1="";
		boolean redirect=false;
		
		String contenido="";
		String url_ant="";
	      //System.out.println("Leyendo Pagina : " + url);
	      StringBuffer resultado = new StringBuffer();
	      int veces = 0;
	 
	      try {
	    	 while(url!=null){
	    		 contenido=getContentUrl(url, ficheroLog);
	    		 
	    		 Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(contenido);
	    		 while (m.find()) {
	    			 if(!m.group().equalsIgnoreCase("feross@feross.org")){
	    				 if(resultado.indexOf(m.group())==-1){
	    					 veces++;
	    					 resultado.append(String.valueOf(m.group()));
	    					 resultado.append("; ");
	    				 }
	    			 }
	    		 }
	    		 if(veces==0){
	    			 if(contenido.toLowerCase().indexOf(cadena_redirect)!=-1 ||contenido.indexOf(cadena_redirect1)!=-1 ||contenido.indexOf(cadena_redirect2)!=-1 ){ 	        			 

	    				 while(contenido.indexOf("http")!=-1){
	    					 String url_redirect_aux=contenido.substring(contenido.indexOf("http"));
	    					 contenido=contenido.substring(contenido.indexOf("http")+5);
	 	        			 if(url_redirect_aux.contains("\""))
	 	        				url_redirect_aux=url_redirect_aux.substring(0, url_redirect_aux.indexOf("\""));
	 	        			 url_redirect_aux = java.net.URLDecoder.decode(url_redirect_aux, StandardCharsets.UTF_8.name());
	 	        			 if((url_redirect_aux.indexOf("//")!=-1 || url_redirect_aux.indexOf("%2F%2")!=-1)&& !url_redirect_aux.endsWith(".js") && !url_redirect_aux.endsWith(".css") && !url_redirect_aux.endsWith(".dtd")){
	 	        				url_redirect= url_redirect_aux;
	 	        				break;
	 	        			 }
	    				 }
	    			 }
	    		 }
	    		 if(url_redirect.equalsIgnoreCase("") || url_redirect.equals(url_ant)){
	    			 url=null;
	    		 }else{
	    			 while(url_redirect.indexOf("&amp;")!=-1){
	    				 url_redirect=url_redirect.substring(0, url_redirect.indexOf("&amp;"))+"&"+url_redirect.substring(url_redirect.indexOf("&amp;")+"&amp;".length());
	    			 }
	    			 url=url_redirect;
	    			 url_ant=url_redirect;
	    		 }
	    	 }
	      }catch (Exception e){
	    	  e.printStackTrace();
	      }
	      return resultado.toString();
	   }
	
	
	
	public static String getContentUrl(String url, File ficheroLog) throws MiExcepcion
	{
		String contenido="";
		try{
			url=url.replaceAll("'", "");
			//String url="https://journals.sagepub.com/doi/10.1177/1745691612459518";
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");

			//System.out.println("Request URL ... " + url);

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}

			//System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");

				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");
										
				//System.out.println("Redirect to URL : " + newUrl);

			}

			BufferedReader in = new BufferedReader(
		                              new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();

			//System.out.println("URL Content... \n" + html.toString());
			contenido=html.toString();
			//System.out.println("Done");

		}
		catch(FileNotFoundException ex){
			String data = "URL no encontrada!!" + ex.getMessage();
			escribeFicheroErrores(ficheroLog, data);
		}
		catch(SocketException exc){
			String data = "Error al leer!!" + url+" "+ exc.getMessage();
			escribeFicheroErrores(ficheroLog, data);
		}
		catch(java.io.IOException exx){
			String data = "Error al leer!!" + url+" "+ exx.getMessage();
			escribeFicheroErrores(ficheroLog, data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return contenido;
	}
	
	public static void escribirExcel(ArrayList<String> columns, ArrayList<ArrayList<String>> contents, String fichero){
		try{
			Workbook workbook = new XSSFWorkbook();
		    Sheet sheet = workbook.createSheet("Contacts");

		    Font headerFont = workbook.createFont();
		    headerFont.setBold(true);
		    headerFont.setFontHeightInPoints((short) 14);
		    headerFont.setColor(IndexedColors.RED.getIndex());

		    CellStyle headerCellStyle = workbook.createCellStyle();
		    headerCellStyle.setFont(headerFont);

		    // Create a Row
		    Row headerRow = sheet.createRow(0);

		    for (int i = 0; i < columns.size(); i++) {
		      Cell cell = headerRow.createCell(i);
		      cell.setCellValue(columns.get(i));
		      cell.setCellStyle(headerCellStyle);
		    }

		    // Create Other rows and cells with contacts data
		    int rowNum = 1;

		    for (ArrayList<String> content : contents) {
		      Row row = sheet.createRow(rowNum++);
		      for (int j=0; j<content.size(); j++){
		    	  row.createCell(j).setCellValue(content.get(j));
		      }
		    }

		    // Resize all columns to fit the content size
		    for (int i = 0; i < columns.size(); i++) {
		      sheet.autoSizeColumn(i);
		    }

		    // Write the output to a file
		    FileOutputStream fileOut = new FileOutputStream(fichero);
		    workbook.write(fileOut);
		    workbook.close();
		    fileOut.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
	
	public static String transformar(String fileXslt, String fileT, String fileSalida, HashMap<String,String> reemplazar)
	{
		String text1="";
		try
		{
			URL obj = new URL(fileXslt);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			Source xslt = new StreamSource(in);
			Source text = new StreamSource(new File(fileT));
			TransformerFactory factory     = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(xslt);
			File ficSalida=new File(fileSalida);
			transformer.transform(text, new StreamResult(ficSalida));
			in.close();
			
			/*leemos el fichero y sustituimos lo que toque*/
			StringBuffer buffer = new StringBuffer();
	 		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileSalida), "UTF-8");
	 		Reader in1 = new BufferedReader(isr);
	 	    int ch;
	 	    while ((ch = in1.read()) > -1) {
	 	         buffer.append((char)ch);
	 	    }
	 	    in1.close();
	 		String cadenaSalida = buffer.toString();
	 		if (reemplazar!=null)
	 		{
	 			for ( Entry<String, String> entry : reemplazar.entrySet()) {
	 				String key = entry.getKey();
	 				String value = entry.getValue();
	 				cadenaSalida=cadenaSalida.replaceAll(key, value);
	 			}
	 			
	 			if(ficSalida.exists())
	 				ficSalida.delete();
	 	 		
	 	 		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ficSalida.getAbsolutePath()), "UTF-8"));
	 	 	    out.write(cadenaSalida);
	 	 	    out.close(); 
	 		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
        return text1;
	}
	
	public void SubeFicheroFTP(File ficheroEntrada, String targetfile, JTextArea area) throws MiExcepcion{
		try{
			FTPClient ftpClient = new FTPClient();
			FileInputStream fis = null;

			try {
				ftpClient.connect(hostname);
				ftpClient.login(userFTP, passFTP);
			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			    String localfile=ficheroEntrada.getAbsolutePath();
			    fis = new FileInputStream(localfile);
			    ftpClient.deleteFile(targetfile.replace(".", "_bak."));
			    ftpClient.rename(targetfile, targetfile.replace(".", "_bak."));
	            File firstLocalFile = new File(localfile);
	 
	            String firstRemoteFile = targetfile;
	            InputStream inputStream = new FileInputStream(firstLocalFile);
	 
	            System.out.println("Start uploading first file");
	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();
	            if (done) {
	            	area.setText(area.getText()+"\n\n"+"subido "+targetfile);
	            }

			    ftpClient.logout();
    			} catch (IOException ex) {
    				area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
	    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
	    			area.setText(area.getText()+"\n***ERROR!!***"+ex.getMessage());
    			} finally {
    			    try {
    			        if (fis != null) {
    			            fis.close();
    			        }
    			        ftpClient.disconnect();
    			    } catch (IOException exc) {
    			    	area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
		    			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
		    			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
    			    }
    			}
		}
		catch(Exception exc){
			area.setText(area.getText()+"\n\n"+"error al subir el fichero de "+ficheroEntrada.getName());
			area.setText(area.getText()+"\n\n"+"en el directorio "+targetfile);
			area.setText(area.getText()+"\n***ERROR!!***"+exc.getMessage());
			throw new MiExcepcion();
		}
	}
	public static void main(String[] args) {
		
		//transformar("https://journals.copmadrid.org/jats_to_doi.xsl","C:\\Users\\ehormigo\\Desktop\\Ani\\1130-5274-clinsa-30-1-0001.xml","salida.xml", reemplazar);
		JATSProcessor V = new JATSProcessor();      // creamos una ventana
        V.setVisible(true);             // hacemos visible la ventana creada
        
    }
	public class AboutDialog extends JDialog implements ActionListener {
		private static final long serialVersionUID = 1L;
		public AboutDialog(JFrame parent, String title, String message) {
		    super(parent, title, true);
		    if (parent != null) {
		      Dimension parentSize = parent.getSize(); 
		      Point p = parent.getLocation(); 
		      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		    }
		    JPanel messagePane = new JPanel();
		    messagePane.add(new JLabel(message));
		    getContentPane().add(messagePane);
		    JPanel buttonPane = new JPanel();
		    JButton button = new JButton("OK"); 
		    buttonPane.add(button); 
		    button.addActionListener(this);
		    getContentPane().add(buttonPane, BorderLayout.SOUTH);
		    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		    pack(); 
		    setVisible(true);
		  }
		  public void actionPerformed(ActionEvent e) {
		    setVisible(false); 
		    dispose(); 
		  }
		}
	public class MiExcepcion extends Exception{
	    
		private static final long serialVersionUID = 1L;

		// Constructor
	    public MiExcepcion(){};
	    
	    public MiExcepcion(File ficheroLog, String mensaje){
	    	super();
	    	BufferedWriter bw = null;
	        FileWriter fw = null;

	        try {
	            String data = mensaje;
	            // Si el archivo no existe, se crea!
	            if (!ficheroLog.exists()) {
	            	ficheroLog.createNewFile();
	            }
	            // flag true, indica adjuntar información al archivo.
	            fw = new FileWriter(ficheroLog.getAbsoluteFile(), true);
	            bw = new BufferedWriter(fw);
	            bw.write("\n");
	            bw.write(data);
	            System.out.println("información agregada!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                            //Cierra instancias de FileWriter y BufferedWriter
	                if (bw != null)
	                    bw.close();
	                if (fw != null)
	                    fw.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    
	    // Excepcion: Error Provocado
	    public String excErrorPersonalizado(){
	        return "Error capturado ."+this.getMessage();
	    }
	}
	
	public static void guardaConFormato(Document document, String URI){
	    try {
	        TransformerFactory transFact = TransformerFactory.newInstance();

	        //Formateamos el fichero. Añadimos sangrado y la cabecera de XML
	        transFact.setAttribute("indent-number", new Integer(3));
	        Transformer trans = transFact.newTransformer();
	        trans.setOutputProperty(OutputKeys.INDENT, "yes");
	        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

	        //Hacemos la transformación
	        StringWriter sw = new StringWriter();
	        StreamResult sr = new StreamResult(sw);
	        DOMSource domSource = new DOMSource(document);
	        trans.transform(domSource, sr);

	        //Mostrar información a guardar por consola (opcional)
	        //Result console= new StreamResult(System.out);
	        //trans.transform(domSource, console);
	        try {
	            //Creamos fichero para escribir en modo texto
	            //PrintWriter writer = new PrintWriter(new FileWriter(URI));
	            OutputStream os = new FileOutputStream(URI);
	            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
	            //PrintWriter writer1 = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true);

	            //Escribimos todo el árbol en el fichero
	            writer.println(sw.toString());

	            //Cerramos el fichero
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static String devuelveCharFromHTML(String cadena) {
		String cedenaVuelta="";
		HashMap<String,Character> mapaInverso = new HashMap<String,Character>();
    	mapaInverso.put("&ldquo;",'“');
    	mapaInverso.put("&rdquo;",'”');
    	mapaInverso.put("&excl;",'!');
    	mapaInverso.put("&percnt;",'%');
    	mapaInverso.put("&quest;",'?');
    	mapaInverso.put("&commat;",'@');
    	mapaInverso.put("&lsqb;",'[');
    	mapaInverso.put("&bsol;",'\\');
    	mapaInverso.put("&rsqb;",']');
    	mapaInverso.put("&Hat;",'^');
    	mapaInverso.put("&grave;",'`');
    	mapaInverso.put("&lcub;",'{');
    	mapaInverso.put("&verbar;",'|');
    	mapaInverso.put("&rcub;",'}');
    	mapaInverso.put("&iexcl;",'¡');
    	mapaInverso.put("&cent;",'¢');
    	mapaInverso.put("&pound;",'£');
    	mapaInverso.put("&curren;",'¤');
    	mapaInverso.put("&yen;",'¥');
    	mapaInverso.put("&brvbar;",'¦');
    	mapaInverso.put("&sect;",'§');
    	mapaInverso.put("&Dot;",'¨');
    	mapaInverso.put("&copy;",'©');
    	mapaInverso.put("&ordf;",'ª');
    	mapaInverso.put("&laquo;",'«');
    	mapaInverso.put("&not;",'¬');
    	mapaInverso.put("&reg;",'®');
    	mapaInverso.put("&macr;",'¯');
    	mapaInverso.put("&deg;",'°');
    	mapaInverso.put("&plusmn;",'±');
    	mapaInverso.put("&sup2;",'²');
    	mapaInverso.put("&sup3;",'³');
    	mapaInverso.put("&acute;",'´');
    	mapaInverso.put("&micro;",'µ');
    	mapaInverso.put("&para;",'¶');
    	mapaInverso.put("&middot;",'·');
    	mapaInverso.put("&cedil;",'¸');
    	mapaInverso.put("&sup1;",'¹');
    	mapaInverso.put("&ordm;",'º');
    	mapaInverso.put("&raquo;",'»');
    	mapaInverso.put("&frac14;",'¼');
    	mapaInverso.put("&frac12;",'½');
    	mapaInverso.put("&frac34;",'¾');
    	mapaInverso.put("&iquest;",'¿');
    	mapaInverso.put("&Agrave;",'À');
    	mapaInverso.put("&Aacute;",'Á');
    	mapaInverso.put("&Acirc;",'Â');
    	mapaInverso.put("&Atilde;",'Ã');
    	mapaInverso.put("&Auml;",'Ä');
    	mapaInverso.put("&Aring;",'Å');
    	mapaInverso.put("&AElig;",'Æ');
    	mapaInverso.put("&Ccedil;",'Ç');
    	mapaInverso.put("&Egrave;",'È');
    	mapaInverso.put("&Eacute;",'É');
    	mapaInverso.put("&Ecirc;",'Ê');
    	mapaInverso.put("&Euml;",'Ë');
    	mapaInverso.put("&Igrave;",'Ì');
    	mapaInverso.put("&Iacute;",'Í');
    	mapaInverso.put("&Icirc;",'Î');
    	mapaInverso.put("&Iuml;",'Ï');
    	mapaInverso.put("&ETH;",'Ð');
    	mapaInverso.put("&Ntilde;",'Ñ');
    	mapaInverso.put("&Ograve;",'Ò');
    	mapaInverso.put("&Oacute;",'Ó');
    	mapaInverso.put("&Ocirc;",'Ô');
    	mapaInverso.put("&Otilde;",'Õ');
    	mapaInverso.put("&Ouml;",'Ö');
    	mapaInverso.put("&times;",'×');
    	mapaInverso.put("&Oslash;",'Ø');
    	mapaInverso.put("&Ugrave;",'Ù');
    	mapaInverso.put("&Uacute;",'Ú');
    	mapaInverso.put("&Ucirc;",'Û');
    	mapaInverso.put("&Uuml;",'Ü');
    	mapaInverso.put("&Yacute;",'Ý');
    	mapaInverso.put("&THORN;",'Þ');
    	mapaInverso.put("&szlig;",'ß');
    	mapaInverso.put("&agrave;",'à');
    	mapaInverso.put("&aacute;",'á');
    	mapaInverso.put("&acirc;",'â');
    	mapaInverso.put("&atilde;",'ã');
    	mapaInverso.put("&auml;",'ä');
    	mapaInverso.put("&aring;",'å');
    	mapaInverso.put("&aelig;",'æ');
    	mapaInverso.put("&ccedil;",'ç');
    	mapaInverso.put("&egrave;",'è');
    	mapaInverso.put("&eacute;",'é');
    	mapaInverso.put("&ecirc;",'ê');
    	mapaInverso.put("&euml;",'ë');
    	mapaInverso.put("&igrave;",'ì');
    	mapaInverso.put("&iacute;",'í');
    	mapaInverso.put("&icirc;",'î');
    	mapaInverso.put("&iuml;",'ï');
    	mapaInverso.put("&eth;",'ð');
    	mapaInverso.put("&ntilde;",'ñ');
    	mapaInverso.put("&ograve;",'ò');
    	mapaInverso.put("&oacute;",'ó');
    	mapaInverso.put("&ocirc;",'ô');
    	mapaInverso.put("&otilde;",'õ');
    	mapaInverso.put("&ouml;",'ö');
    	mapaInverso.put("&divide;",'÷');
    	mapaInverso.put("&oslash;",'ø');
    	mapaInverso.put("&ugrave;",'ù');
    	mapaInverso.put("&uacute;",'ú');
    	mapaInverso.put("&ucirc;",'û');
    	mapaInverso.put("&uuml;",'ü');
    	mapaInverso.put("&yacute;",'ý');
    	mapaInverso.put("&thorn;",'þ');
    	mapaInverso.put("&yuml;",'ÿ');
    	mapaInverso.put("&Amacr;",'Ā');
    	mapaInverso.put("&amacr;",'ā');
    	mapaInverso.put("&Abreve;",'Ă');
    	mapaInverso.put("&abreve;",'ă');
    	mapaInverso.put("&Aogon;",'Ą');
    	mapaInverso.put("&aogon;",'ą');
    	mapaInverso.put("&Cacute;",'Ć');
    	mapaInverso.put("&cacute;",'ć');
    	mapaInverso.put("&Ccirc;",'Ĉ');
    	mapaInverso.put("&ccirc;",'ĉ');
    	mapaInverso.put("&Cdot;",'Ċ');
    	mapaInverso.put("&cdot;",'ċ');
    	mapaInverso.put("&Ccaron;",'Č');
    	mapaInverso.put("&ccaron;",'č');
    	mapaInverso.put("&Dcaron;",'Ď');
    	mapaInverso.put("&dcaron;",'ď');
    	mapaInverso.put("&Dstrok;",'Đ');
    	mapaInverso.put("&dstrok;",'đ');
    	mapaInverso.put("&Emacr;",'Ē');
    	mapaInverso.put("&emacr;",'ē');
    	mapaInverso.put("&Edot;",'Ė');
    	mapaInverso.put("&edot;",'ė');
    	mapaInverso.put("&Eogon;",'Ę');
    	mapaInverso.put("&eogon;",'ę');
    	mapaInverso.put("&Ecaron;",'Ě');
    	mapaInverso.put("&ecaron;",'ě');
    	mapaInverso.put("&Gcirc;",'Ĝ');
    	mapaInverso.put("&gcirc;",'ĝ');
    	mapaInverso.put("&Gbreve;",'Ğ');
    	mapaInverso.put("&gbreve;",'ğ');
    	mapaInverso.put("&Gdot;",'Ġ');
    	mapaInverso.put("&gdot;",'ġ');
    	mapaInverso.put("&Gcedil;",'Ģ');
    	mapaInverso.put("&Hcirc;",'Ĥ');
    	mapaInverso.put("&hcirc;",'ĥ');
    	mapaInverso.put("&Hstrok;",'Ħ');
    	mapaInverso.put("&hstrok;",'ħ');
    	mapaInverso.put("&Itilde;",'Ĩ');
    	mapaInverso.put("&itilde;",'ĩ');
    	mapaInverso.put("&Imacr;",'Ī');
    	mapaInverso.put("&imacr;",'ī');
    	mapaInverso.put("&Iogon;",'Į');
    	mapaInverso.put("&iogon;",'į');
    	mapaInverso.put("&Idot;",'İ');
    	mapaInverso.put("&imath;",'ı');
    	mapaInverso.put("&IJlig;",'Ĳ');
    	mapaInverso.put("&ijlig;",'ĳ');
    	mapaInverso.put("&Jcirc;",'Ĵ');
    	mapaInverso.put("&jcirc;",'ĵ');
    	mapaInverso.put("&Kcedil;",'Ķ');
    	mapaInverso.put("&kcedil;",'ķ');
    	mapaInverso.put("&kgreen;",'ĸ');
    	mapaInverso.put("&Lacute;",'Ĺ');
    	mapaInverso.put("&lacute;",'ĺ');
    	mapaInverso.put("&Lcedil;",'Ļ');
    	mapaInverso.put("&lcedil;",'ļ');
    	mapaInverso.put("&Lcaron;",'Ľ');
    	mapaInverso.put("&lcaron;",'ľ');
    	mapaInverso.put("&Lmidot;",'Ŀ');
    	mapaInverso.put("&lmidot;",'ŀ');
    	mapaInverso.put("&Lstrok;",'Ł');
    	mapaInverso.put("&lstrok;",'ł');
    	mapaInverso.put("&Nacute;",'Ń');
    	mapaInverso.put("&nacute;",'ń');
    	mapaInverso.put("&Ncedil;",'Ņ');
    	mapaInverso.put("&ncedil;",'ņ');
    	mapaInverso.put("&Ncaron;",'Ň');
    	mapaInverso.put("&ncaron;",'ň');
    	mapaInverso.put("&napos;",'ŉ');
    	mapaInverso.put("&ENG;",'Ŋ');
    	mapaInverso.put("&eng;",'ŋ');
    	mapaInverso.put("&Omacr;",'Ō');
    	mapaInverso.put("&omacr;",'ō');
    	mapaInverso.put("&Odblac;",'Ő');
    	mapaInverso.put("&odblac;",'ő');
    	mapaInverso.put("&OElig;",'Œ');
    	mapaInverso.put("&oelig;",'œ');
    	mapaInverso.put("&Racute;",'Ŕ');
    	mapaInverso.put("&racute;",'ŕ');
    	mapaInverso.put("&Rcedil;",'Ŗ');
    	mapaInverso.put("&rcedil;",'ŗ');
    	mapaInverso.put("&Rcaron;",'Ř');
    	mapaInverso.put("&rcaron;",'ř');
    	mapaInverso.put("&Sacute;",'Ś');
    	mapaInverso.put("&sacute;",'ś');
    	mapaInverso.put("&Scirc;",'Ŝ');
    	mapaInverso.put("&scirc;",'ŝ');
    	mapaInverso.put("&Scedil;",'Ş');
    	mapaInverso.put("&scedil;",'ş');
    	mapaInverso.put("&Scaron;",'Š');
    	mapaInverso.put("&scaron;",'š');
    	mapaInverso.put("&Tcedil;",'Ţ');
    	mapaInverso.put("&tcedil;",'ţ');
    	mapaInverso.put("&Tcaron;",'Ť');
    	mapaInverso.put("&tcaron;",'ť');
    	mapaInverso.put("&Tstrok;",'Ŧ');
    	mapaInverso.put("&tstrok;",'ŧ');
    	mapaInverso.put("&Utilde;",'Ũ');
    	mapaInverso.put("&utilde;",'ũ');
    	mapaInverso.put("&Umacr;",'Ū');
    	mapaInverso.put("&umacr;",'ū');
    	mapaInverso.put("&Ubreve;",'Ŭ');
    	mapaInverso.put("&ubreve;",'ŭ');
    	mapaInverso.put("&Uring;",'Ů');
    	mapaInverso.put("&uring;",'ů');
    	mapaInverso.put("&Udblac;",'Ű');
    	mapaInverso.put("&udblac;",'ű');
    	mapaInverso.put("&Uogon;",'Ų');
    	mapaInverso.put("&uogon;",'ų');
    	mapaInverso.put("&Wcirc;",'Ŵ');
    	mapaInverso.put("&wcirc;",'ŵ');
    	mapaInverso.put("&Ycirc;",'Ŷ');
    	mapaInverso.put("&ycirc;",'ŷ');
    	mapaInverso.put("&Yuml;",'Ÿ');
    	mapaInverso.put("&Zacute;",'Ź');
    	mapaInverso.put("&zacute;",'ź');
    	mapaInverso.put("&Zdot;",'Ż');
    	mapaInverso.put("&zdot;",'ż');
    	mapaInverso.put("&Zcaron;",'Ž');
    	mapaInverso.put("&zcaron;",'ž');
    	mapaInverso.put("&fnof;",'ƒ');
    	mapaInverso.put("&imped;",'Ƶ');
    	mapaInverso.put("&gacute;",'ǵ');
    	mapaInverso.put("&jmath;",'ȷ');
    	mapaInverso.put("&circ;",'ˆ');
    	mapaInverso.put("&caron;",'ˇ');
    	mapaInverso.put("&breve;",'˘');
    	mapaInverso.put("&dot;",'˙');
    	mapaInverso.put("&ring;",'˚');
    	mapaInverso.put("&ogon;",'˛');
    	mapaInverso.put("&tilde;",'˜');
    	mapaInverso.put("&dblac;",'˝');
    	mapaInverso.put("&Alpha;",'Α');
    	mapaInverso.put("&Beta;",'Β');
    	mapaInverso.put("&Gamma;",'Γ');
    	mapaInverso.put("&Delta;",'Δ');
    	mapaInverso.put("&Delta;",'∆');
    	mapaInverso.put("&Epsilon;",'Ε');
    	mapaInverso.put("&Zeta;",'Ζ');
    	mapaInverso.put("&Eta;",'Η');
    	mapaInverso.put("&Theta;",'Θ');
    	mapaInverso.put("&Iota;",'Ι');
    	mapaInverso.put("&Kappa;",'Κ');
    	mapaInverso.put("&Lambda;",'Λ');
    	mapaInverso.put("&Mu;",'Μ');
    	mapaInverso.put("&Nu;",'Ν');
    	mapaInverso.put("&Xi;",'Ξ');
    	mapaInverso.put("&Omicron;",'Ο');
    	mapaInverso.put("&Pi;",'Π');
    	mapaInverso.put("&Rho;",'Ρ');
    	mapaInverso.put("&Sigma;",'Σ');
    	mapaInverso.put("&Tau;",'Τ');
    	mapaInverso.put("&Upsilon;",'Υ');
    	mapaInverso.put("&Phi;",'Φ');
    	mapaInverso.put("&Chi;",'Χ');
    	mapaInverso.put("&Psi;",'Ψ');
    	mapaInverso.put("&Omega;",'Ω');
    	mapaInverso.put("&alpha;",'α');
    	mapaInverso.put("&beta;",'β');
    	mapaInverso.put("&gamma;",'γ');
    	mapaInverso.put("&delta;",'δ');
    	mapaInverso.put("&epsiv;",'ε');
    	mapaInverso.put("&zeta;",'ζ');
    	mapaInverso.put("&eta;",'η');
    	mapaInverso.put("&eta;",'ƞ');
    	mapaInverso.put("&theta;",'θ');
    	mapaInverso.put("&iota;",'ι');
    	mapaInverso.put("&kappa;",'κ');
    	mapaInverso.put("&lambda;",'λ');
    	mapaInverso.put("&mu;",'μ');
    	mapaInverso.put("&nu;",'ν');
    	mapaInverso.put("&xi;",'ξ');
    	mapaInverso.put("&omicron;",'ο');
    	mapaInverso.put("&pi;",'π');
    	mapaInverso.put("&rho;",'ρ');
    	mapaInverso.put("&sigmav;",'ς');
    	mapaInverso.put("&sigma;",'σ');
    	mapaInverso.put("&tau;",'τ');
    	mapaInverso.put("&upsi;",'υ');
    	mapaInverso.put("&phi;",'φ');
    	mapaInverso.put("&chi;",'χ');
    	mapaInverso.put("&psi;",'ψ');
    	mapaInverso.put("&omega;",'ω');
    	mapaInverso.put("&thetav;",'ϑ');
    	mapaInverso.put("&Upsi;",'ϒ');
    	mapaInverso.put("&straightphi;",'ϕ');
    	mapaInverso.put("&piv;",'ϖ');
    	mapaInverso.put("&Gammad;",'Ϝ');
    	mapaInverso.put("&gammad;",'ϝ');
    	mapaInverso.put("&kappav;",'ϰ');
    	mapaInverso.put("&rhov;",'ϱ');
    	mapaInverso.put("&epsi;",'ϵ');
    	mapaInverso.put("&bepsi;",'϶');
    	mapaInverso.put("&IOcy;",'Ё');
    	mapaInverso.put("&DJcy;",'Ђ');
    	mapaInverso.put("&GJcy;",'Ѓ');
    	mapaInverso.put("&Jukcy;",'Є');
    	mapaInverso.put("&DScy;",'Ѕ');
    	mapaInverso.put("&Iukcy;",'І');
    	mapaInverso.put("&YIcy;",'Ї');
    	mapaInverso.put("&Jsercy;",'Ј');
    	mapaInverso.put("&LJcy;",'Љ');
    	mapaInverso.put("&NJcy;",'Њ');
    	mapaInverso.put("&TSHcy;",'Ћ');
    	mapaInverso.put("&KJcy;",'Ќ');
    	mapaInverso.put("&Ubrcy;",'Ў');
    	mapaInverso.put("&DZcy;",'Џ');
    	mapaInverso.put("&Acy;",'А');
    	mapaInverso.put("&Bcy;",'Б');
    	mapaInverso.put("&Vcy;",'В');
    	mapaInverso.put("&Gcy;",'Г');
    	mapaInverso.put("&Dcy;",'Д');
    	mapaInverso.put("&IEcy;",'Е');
    	mapaInverso.put("&ZHcy;",'Ж');
    	mapaInverso.put("&Zcy;",'З');
    	mapaInverso.put("&Icy;",'И');
    	mapaInverso.put("&Jcy;",'Й');
    	mapaInverso.put("&Kcy;",'К');
    	mapaInverso.put("&Lcy;",'Л');
    	mapaInverso.put("&Mcy;",'М');
    	mapaInverso.put("&Ncy;",'Н');
    	mapaInverso.put("&Ocy;",'О');
    	mapaInverso.put("&Pcy;",'П');
    	mapaInverso.put("&Rcy;",'Р');
    	mapaInverso.put("&Scy;",'С');
    	mapaInverso.put("&Tcy;",'Т');
    	mapaInverso.put("&Ucy;",'У');
    	mapaInverso.put("&Fcy;",'Ф');
    	mapaInverso.put("&KHcy;",'Х');
    	mapaInverso.put("&TScy;",'Ц');
    	mapaInverso.put("&CHcy;",'Ч');
    	mapaInverso.put("&SHcy;",'Ш');
    	mapaInverso.put("&SHCHcy;",'Щ');
    	mapaInverso.put("&HARDcy;",'Ъ');
    	mapaInverso.put("&Ycy;",'Ы');
    	mapaInverso.put("&SOFTcy;",'Ь');
    	mapaInverso.put("&Ecy;",'Э');
    	mapaInverso.put("&YUcy;",'Ю');
    	mapaInverso.put("&YAcy;",'Я');
    	mapaInverso.put("&acy;",'а');
    	mapaInverso.put("&bcy;",'б');
    	mapaInverso.put("&vcy;",'в');
    	mapaInverso.put("&gcy;",'г');
    	mapaInverso.put("&dcy;",'д');
    	mapaInverso.put("&iecy;",'е');
    	mapaInverso.put("&zhcy;",'ж');
    	mapaInverso.put("&zcy;",'з');
    	mapaInverso.put("&icy;",'и');
    	mapaInverso.put("&jcy;",'й');
    	mapaInverso.put("&kcy;",'к');
    	mapaInverso.put("&lcy;",'л');
    	mapaInverso.put("&mcy;",'м');
    	mapaInverso.put("&ncy;",'н');
    	mapaInverso.put("&ocy;",'о');
    	mapaInverso.put("&pcy;",'п');
    	mapaInverso.put("&rcy;",'р');
    	mapaInverso.put("&scy;",'с');
    	mapaInverso.put("&tcy;",'т');
    	mapaInverso.put("&ucy;",'у');
    	mapaInverso.put("&fcy;",'ф');
    	mapaInverso.put("&khcy;",'х');
    	mapaInverso.put("&tscy;",'ц');
    	mapaInverso.put("&chcy;",'ч');
    	mapaInverso.put("&shcy;",'ш');
    	mapaInverso.put("&shchcy;",'щ');
    	mapaInverso.put("&hardcy;",'ъ');
    	mapaInverso.put("&ycy;",'ы');
    	mapaInverso.put("&softcy;",'ь');
    	mapaInverso.put("&ecy;",'э');
    	mapaInverso.put("&yucy;",'ю');
    	mapaInverso.put("&yacy;",'я');
    	mapaInverso.put("&iocy;",'ё');
    	mapaInverso.put("&djcy;",'ђ');
    	mapaInverso.put("&gjcy;",'ѓ');
    	mapaInverso.put("&jukcy;",'є');
    	mapaInverso.put("&dscy;",'ѕ');
    	mapaInverso.put("&iukcy;",'і');
    	mapaInverso.put("&yicy;",'ї');
    	mapaInverso.put("&jsercy;",'ј');
    	mapaInverso.put("&ljcy;",'љ');
    	mapaInverso.put("&njcy;",'њ');
    	mapaInverso.put("&tshcy;",'ћ');
    	mapaInverso.put("&kjcy;",'ќ');
    	mapaInverso.put("&ubrcy;",'ў');
    	mapaInverso.put("&dzcy;",'џ');
    	mapaInverso.put("&hyphen;",'‐');
    	mapaInverso.put("&ndash;",'–');
    	mapaInverso.put("&mdash;",'—');
    	mapaInverso.put("&horbar;",'―');
    	mapaInverso.put("&Verbar;",'‖');
    	mapaInverso.put("&lsquo;",'‘');
    	mapaInverso.put("&rsquo;",'’');
    	mapaInverso.put("&lsquor;",'‚');
    	mapaInverso.put("&ldquo;",'“');
    	mapaInverso.put("&rdquo;",'”');
    	mapaInverso.put("&ldquor;",'„');
    	mapaInverso.put("&dagger;",'†');
    	mapaInverso.put("&Dagger;",'‡');
    	mapaInverso.put("&bull;",'•');
    	mapaInverso.put("&nldr;",'‥');
    	mapaInverso.put("&hellip;",'…');
    	mapaInverso.put("&permil;",'‰');
    	mapaInverso.put("&pertenk;",'‱');
    	mapaInverso.put("&prime;",'′');
    	mapaInverso.put("&Prime;",'″');
    	mapaInverso.put("&tprime;",'‴');
    	mapaInverso.put("&bprime;",'‵');
    	mapaInverso.put("&lsaquo;",'‹');
    	mapaInverso.put("&rsaquo;",'›');
    	mapaInverso.put("&oline;",'‾');
    	mapaInverso.put("&caret;",'⁁');
    	mapaInverso.put("&hybull;",'⁃');
    	mapaInverso.put("&bsemi;",'⁏');
    	mapaInverso.put("&qprime;",'⁗');
    	mapaInverso.put("&euro;",'€');
    	mapaInverso.put("&Copf;",'ℂ');
    	mapaInverso.put("&incare;",'℅');
    	mapaInverso.put("&gscr;",'ℊ');
    	mapaInverso.put("&hamilt;",'ℋ');
    	mapaInverso.put("&Hfr;",'ℌ');
    	mapaInverso.put("&quaternions;",'ℍ');
    	mapaInverso.put("&planckh;",'ℎ');
    	mapaInverso.put("&planck;",'ℏ');
    	mapaInverso.put("&Iscr;",'ℐ');
    	mapaInverso.put("&image;",'ℑ');
    	mapaInverso.put("&Lscr;",'ℒ');
    	mapaInverso.put("&ell;",'ℓ');
    	mapaInverso.put("&Nopf;",'ℕ');
    	mapaInverso.put("&numero;",'№');
    	mapaInverso.put("&copysr;",'℗');
    	mapaInverso.put("&weierp;",'℘');
    	mapaInverso.put("&Popf;",'ℙ');
    	mapaInverso.put("&rationals;",'ℚ');
    	mapaInverso.put("&Rscr;",'ℛ');
    	mapaInverso.put("&real;",'ℜ');
    	mapaInverso.put("&reals;",'ℝ');
    	mapaInverso.put("&rx;",'℞');
    	mapaInverso.put("&trade;",'™');
    	mapaInverso.put("&integers;",'ℤ');
    	mapaInverso.put("&ohm;",'Ω');
    	mapaInverso.put("&mho;",'℧');
    	mapaInverso.put("&Zfr;",'ℨ');
    	mapaInverso.put("&iiota;",'℩');
    	mapaInverso.put("&angst;",'Å');
    	mapaInverso.put("&bernou;",'ℬ');
    	mapaInverso.put("&Cfr;",'ℭ');
    	mapaInverso.put("&escr;",'ℯ');
    	mapaInverso.put("&Escr;",'ℰ');
    	mapaInverso.put("&Fscr;",'ℱ');
    	mapaInverso.put("&phmmat;",'ℳ');
    	mapaInverso.put("&order;",'ℴ');
    	mapaInverso.put("&alefsym;",'ℵ');
    	mapaInverso.put("&beth;",'ℶ');
    	mapaInverso.put("&gimel;",'ℷ');
    	mapaInverso.put("&daleth;",'ℸ');
    	mapaInverso.put("&CapitalDifferentialD;",'ⅅ');
    	mapaInverso.put("&DifferentialD;",'ⅆ');
    	mapaInverso.put("&ExponentialE;",'ⅇ');
    	mapaInverso.put("&ImaginaryI;",'ⅈ');
    	mapaInverso.put("&frac13;",'⅓');
    	mapaInverso.put("&frac23;",'⅔');
    	mapaInverso.put("&frac15;",'⅕');
    	mapaInverso.put("&frac25;",'⅖');
    	mapaInverso.put("&frac35;",'⅗');
    	mapaInverso.put("&frac45;",'⅘');
    	mapaInverso.put("&frac16;",'⅙');
    	mapaInverso.put("&frac56;",'⅚');
    	mapaInverso.put("&frac18;",'⅛');
    	mapaInverso.put("&frac38;",'⅜');
    	mapaInverso.put("&frac58;",'⅝');
    	mapaInverso.put("&frac78;",'⅞');
    	mapaInverso.put("&larr;",'←');
    	mapaInverso.put("&uarr;",'↑');
    	mapaInverso.put("&rarr;",'→');
    	mapaInverso.put("&darr;",'↓');
    	mapaInverso.put("&harr;",'↔');
    	mapaInverso.put("&varr;",'↕');
    	mapaInverso.put("&nwarr;",'↖');
    	mapaInverso.put("&nearr;",'↗');
    	mapaInverso.put("&searr;",'↘');
    	mapaInverso.put("&swarr;",'↙');
    	mapaInverso.put("&nlarr;",'↚');
    	mapaInverso.put("&nrarr;",'↛');
    	mapaInverso.put("&rarrw;",'↝');
    	mapaInverso.put("&Larr;",'↞');
    	mapaInverso.put("&Uarr;",'↟');
    	mapaInverso.put("&Rarr;",'↠');
    	mapaInverso.put("&Darr;",'↡');
    	mapaInverso.put("&larrtl;",'↢');
    	mapaInverso.put("&rarrtl;",'↣');
    	mapaInverso.put("&LeftTeeArrow;",'↤');
    	mapaInverso.put("&UpTeeArrow;",'↥');
    	mapaInverso.put("&map;",'↦');
    	mapaInverso.put("&DownTeeArrow;",'↧');
    	mapaInverso.put("&larrhk;",'↩');
    	mapaInverso.put("&rarrhk;",'↪');
    	mapaInverso.put("&larrlp;",'↫');
    	mapaInverso.put("&rarrlp;",'↬');
    	mapaInverso.put("&harrw;",'↭');
    	mapaInverso.put("&nharr;",'↮');
    	mapaInverso.put("&lsh;",'↰');
    	mapaInverso.put("&rsh;",'↱');
    	mapaInverso.put("&ldsh;",'↲');
    	mapaInverso.put("&rdsh;",'↳');
    	mapaInverso.put("&crarr;",'↵');
    	mapaInverso.put("&cularr;",'↶');
    	mapaInverso.put("&curarr;",'↷');
    	mapaInverso.put("&olarr;",'↺');
    	mapaInverso.put("&orarr;",'↻');
    	mapaInverso.put("&lharu;",'↼');
    	mapaInverso.put("&lhard;",'↽');
    	mapaInverso.put("&uharr;",'↾');
    	mapaInverso.put("&uharl;",'↿');
    	mapaInverso.put("&rharu;",'⇀');
    	mapaInverso.put("&rhard;",'⇁');
    	mapaInverso.put("&dharr;",'⇂');
    	mapaInverso.put("&dharl;",'⇃');
    	mapaInverso.put("&rlarr;",'⇄');
    	mapaInverso.put("&udarr;",'⇅');
    	mapaInverso.put("&lrarr;",'⇆');
    	mapaInverso.put("&llarr;",'⇇');
    	mapaInverso.put("&uuarr;",'⇈');
    	mapaInverso.put("&rrarr;",'⇉');
    	mapaInverso.put("&ddarr;",'⇊');
    	mapaInverso.put("&lrhar;",'⇋');
    	mapaInverso.put("&rlhar;",'⇌');
    	mapaInverso.put("&nlArr;",'⇍');
    	mapaInverso.put("&nhArr;",'⇎');
    	mapaInverso.put("&nrArr;",'⇏');
    	mapaInverso.put("&lArr;",'⇐');
    	mapaInverso.put("&uArr;",'⇑');
    	mapaInverso.put("&rArr;",'⇒');
    	mapaInverso.put("&dArr;",'⇓');
    	mapaInverso.put("&hArr;",'⇔');
    	mapaInverso.put("&vArr;",'⇕');
    	mapaInverso.put("&nwArr;",'⇖');
    	mapaInverso.put("&neArr;",'⇗');
    	mapaInverso.put("&seArr;",'⇘');
    	mapaInverso.put("&swArr;",'⇙');
    	mapaInverso.put("&lAarr;",'⇚');
    	mapaInverso.put("&rAarr;",'⇛');
    	mapaInverso.put("&zigrarr;",'⇝');
    	mapaInverso.put("&larrb;",'⇤');
    	mapaInverso.put("&rarrb;",'⇥');
    	mapaInverso.put("&duarr;",'⇵');
    	mapaInverso.put("&loarr;",'⇽');
    	mapaInverso.put("&roarr;",'⇾');
    	mapaInverso.put("&hoarr;",'⇿');
    	mapaInverso.put("&forall;",'∀');
    	mapaInverso.put("&comp;",'∁');
    	mapaInverso.put("&part;",'∂');
    	mapaInverso.put("&exist;",'∃');
    	mapaInverso.put("&nexist;",'∄');
    	mapaInverso.put("&empty;",'∅');
    	mapaInverso.put("&nabla;",'∇');
    	mapaInverso.put("&isin;",'∈');
    	mapaInverso.put("&notin;",'∉');
    	mapaInverso.put("&niv;",'∋');
    	mapaInverso.put("&notni;",'∌');
    	mapaInverso.put("&prod;",'∏');
    	mapaInverso.put("&coprod;",'∐');
    	mapaInverso.put("&sum;",'∑');
    	mapaInverso.put("&minus;",'−');
    	mapaInverso.put("&mnplus;",'∓');
    	mapaInverso.put("&plusdo;",'∔');
    	mapaInverso.put("&setmn;",'∖');
    	mapaInverso.put("&lowast;",'∗');
    	mapaInverso.put("&compfn;",'∘');
    	mapaInverso.put("&radic;",'√');
    	mapaInverso.put("&prop;",'∝');
    	mapaInverso.put("&infin;",'∞');
    	mapaInverso.put("&angrt;",'∟');
    	mapaInverso.put("&ang;",'∠');
    	mapaInverso.put("&angmsd;",'∡');
    	mapaInverso.put("&angsph;",'∢');
    	mapaInverso.put("&mid;",'∣');
    	mapaInverso.put("&nmid;",'∤');
    	mapaInverso.put("&par;",'∥');
    	mapaInverso.put("&npar;",'∦');
    	mapaInverso.put("&and;",'∧');
    	mapaInverso.put("&or;",'∨');
    	mapaInverso.put("&cap;",'∩');
    	mapaInverso.put("&cup;",'∪');
    	mapaInverso.put("&int;",'∫');
    	mapaInverso.put("&Int;",'∬');
    	mapaInverso.put("&tint;",'∭');
    	mapaInverso.put("&conint;",'∮');
    	mapaInverso.put("&Conint;",'∯');
    	mapaInverso.put("&Cconint;",'∰');
    	mapaInverso.put("&cwint;",'∱');
    	mapaInverso.put("&cwconint;",'∲');
    	mapaInverso.put("&awconint;",'∳');
    	mapaInverso.put("&there4;",'∴');
    	mapaInverso.put("&becaus;",'∵');
    	mapaInverso.put("&ratio;",'∶');
    	mapaInverso.put("&Colon;",'∷');
    	mapaInverso.put("&minusd;",'∸');
    	mapaInverso.put("&mDDot;",'∺');
    	mapaInverso.put("&homtht;",'∻');
    	mapaInverso.put("&sim;",'∼');
    	mapaInverso.put("&bsim;",'∽');
    	mapaInverso.put("&ac;",'∾');
    	mapaInverso.put("&acd;",'∿');
    	mapaInverso.put("&wreath;",'≀');
    	mapaInverso.put("&nsim;",'≁');
    	mapaInverso.put("&esim;",'≂');
    	mapaInverso.put("&sime;",'≃');
    	mapaInverso.put("&nsime;",'≄');
    	mapaInverso.put("&cong;",'≅');
    	mapaInverso.put("&simne;",'≆');
    	mapaInverso.put("&ncong;",'≇');
    	mapaInverso.put("&asymp;",'≈');
    	mapaInverso.put("&nap;",'≉');
    	mapaInverso.put("&ape;",'≊');
    	mapaInverso.put("&apid;",'≋');
    	mapaInverso.put("&bcong;",'≌');
    	mapaInverso.put("&asympeq;",'≍');
    	mapaInverso.put("&bump;",'≎');
    	mapaInverso.put("&bumpe;",'≏');
    	mapaInverso.put("&esdot;",'≐');
    	mapaInverso.put("&eDot;",'≑');
    	mapaInverso.put("&efDot;",'≒');
    	mapaInverso.put("&erDot;",'≓');
    	mapaInverso.put("&colone;",'≔');
    	mapaInverso.put("&ecolon;",'≕');
    	mapaInverso.put("&ecir;",'≖');
    	mapaInverso.put("&cire;",'≗');
    	mapaInverso.put("&wedgeq;",'≙');
    	mapaInverso.put("&veeeq;",'≚');
    	mapaInverso.put("&trie;",'≜');
    	mapaInverso.put("&equest;",'≟');
    	mapaInverso.put("&ne;",'≠');
    	mapaInverso.put("&equiv;",'≡');
    	mapaInverso.put("&nequiv;",'≢');
    	mapaInverso.put("&le;",'≤');
    	mapaInverso.put("&ge;",'≥');
    	mapaInverso.put("&lE;",'≦');
    	mapaInverso.put("&gE;",'≧');
    	mapaInverso.put("&lnE;",'≨');
    	mapaInverso.put("&gnE;",'≩');
    	mapaInverso.put("&Lt;",'≪');
    	mapaInverso.put("&Gt;",'≫');
    	mapaInverso.put("&twixt;",'≬');
    	mapaInverso.put("&NotCupCap;",'≭');
    	mapaInverso.put("&nlt;",'≮');
    	mapaInverso.put("&ngt;",'≯');
    	mapaInverso.put("&nle;",'≰');
    	mapaInverso.put("&nge;",'≱');
    	mapaInverso.put("&lsim;",'≲');
    	mapaInverso.put("&gsim;",'≳');
    	mapaInverso.put("&nlsim;",'≴');
    	mapaInverso.put("&ngsim;",'≵');
    	mapaInverso.put("&lg;",'≶');
    	mapaInverso.put("&gl;",'≷');
    	mapaInverso.put("&ntlg;",'≸');
    	mapaInverso.put("&ntgl;",'≹');
    	mapaInverso.put("&pr;",'≺');
    	mapaInverso.put("&sc;",'≻');
    	mapaInverso.put("&prcue;",'≼');
    	mapaInverso.put("&sccue;",'≽');
    	mapaInverso.put("&prsim;",'≾');
    	mapaInverso.put("&scsim;",'≿');
    	mapaInverso.put("&npr;",'⊀');
    	mapaInverso.put("&nsc;",'⊁');
    	mapaInverso.put("&sub;",'⊂');
    	mapaInverso.put("&sup;",'⊃');
    	mapaInverso.put("&nsub;",'⊄');
    	mapaInverso.put("&nsup;",'⊅');
    	mapaInverso.put("&sube;",'⊆');
    	mapaInverso.put("&supe;",'⊇');
    	mapaInverso.put("&nsube;",'⊈');
    	mapaInverso.put("&nsupe;",'⊉');
    	mapaInverso.put("&subne;",'⊊');
    	mapaInverso.put("&supne;",'⊋');
    	mapaInverso.put("&cupdot;",'⊍');
    	mapaInverso.put("&uplus;",'⊎');
    	mapaInverso.put("&sqsub;",'⊏');
    	mapaInverso.put("&sqsup;",'⊐');
    	mapaInverso.put("&sqsube;",'⊑');
    	mapaInverso.put("&sqsupe;",'⊒');
    	mapaInverso.put("&sqcap;",'⊓');
    	mapaInverso.put("&sqcup;",'⊔');
    	mapaInverso.put("&oplus;",'⊕');
    	mapaInverso.put("&ominus;",'⊖');
    	mapaInverso.put("&otimes;",'⊗');
    	mapaInverso.put("&osol;",'⊘');
    	mapaInverso.put("&odot;",'⊙');
    	mapaInverso.put("&ocir;",'⊚');
    	mapaInverso.put("&oast;",'⊛');
    	mapaInverso.put("&odash;",'⊝');
    	mapaInverso.put("&plusb;",'⊞');
    	mapaInverso.put("&minusb;",'⊟');
    	mapaInverso.put("&timesb;",'⊠');
    	mapaInverso.put("&sdotb;",'⊡');
    	mapaInverso.put("&vdash;",'⊢');
    	mapaInverso.put("&dashv;",'⊣');
    	mapaInverso.put("&top;",'⊤');
    	mapaInverso.put("&bottom;",'⊥');
    	mapaInverso.put("&models;",'⊧');
    	mapaInverso.put("&vDash;",'⊨');
    	mapaInverso.put("&Vdash;",'⊩');
    	mapaInverso.put("&Vvdash;",'⊪');
    	mapaInverso.put("&VDash;",'⊫');
    	mapaInverso.put("&nvdash;",'⊬');
    	mapaInverso.put("&nvDash;",'⊭');
    	mapaInverso.put("&nVdash;",'⊮');
    	mapaInverso.put("&nVDash;",'⊯');
    	mapaInverso.put("&prurel;",'⊰');
    	mapaInverso.put("&vltri;",'⊲');
    	mapaInverso.put("&vrtri;",'⊳');
    	mapaInverso.put("&ltrie;",'⊴');
    	mapaInverso.put("&rtrie;",'⊵');
    	mapaInverso.put("&origof;",'⊶');
    	mapaInverso.put("&imof;",'⊷');
    	mapaInverso.put("&mumap;",'⊸');
    	mapaInverso.put("&hercon;",'⊹');
    	mapaInverso.put("&intcal;",'⊺');
    	mapaInverso.put("&veebar;",'⊻');
    	mapaInverso.put("&barvee;",'⊽');
    	mapaInverso.put("&angrtvb;",'⊾');
    	mapaInverso.put("&lrtri;",'⊿');
    	mapaInverso.put("&xwedge;",'⋀');
    	mapaInverso.put("&xvee;",'⋁');
    	mapaInverso.put("&xcap;",'⋂');
    	mapaInverso.put("&xcup;",'⋃');
    	mapaInverso.put("&diam;",'⋄');
    	mapaInverso.put("&sdot;",'⋅');
    	mapaInverso.put("&sstarf;",'⋆');
    	mapaInverso.put("&divonx;",'⋇');
    	mapaInverso.put("&bowtie;",'⋈');
    	mapaInverso.put("&ltimes;",'⋉');
    	mapaInverso.put("&rtimes;",'⋊');
    	mapaInverso.put("&lthree;",'⋋');
    	mapaInverso.put("&rthree;",'⋌');
    	mapaInverso.put("&bsime;",'⋍');
    	mapaInverso.put("&cuvee;",'⋎');
    	mapaInverso.put("&cuwed;",'⋏');
    	mapaInverso.put("&Sub;",'⋐');
    	mapaInverso.put("&Sup;",'⋑');
    	mapaInverso.put("&Cap;",'⋒');
    	mapaInverso.put("&Cup;",'⋓');
    	mapaInverso.put("&fork;",'⋔');
    	mapaInverso.put("&epar;",'⋕');
    	mapaInverso.put("&ltdot;",'⋖');
    	mapaInverso.put("&gtdot;",'⋗');
    	mapaInverso.put("&Ll;",'⋘');
    	mapaInverso.put("&Gg;",'⋙');
    	mapaInverso.put("&leg;",'⋚');
    	mapaInverso.put("&gel;",'⋛');
    	mapaInverso.put("&cuepr;",'⋞');
    	mapaInverso.put("&cuesc;",'⋟');
    	mapaInverso.put("&nprcue;",'⋠');
    	mapaInverso.put("&nsccue;",'⋡');
    	mapaInverso.put("&nsqsube;",'⋢');
    	mapaInverso.put("&nsqsupe;",'⋣');
    	mapaInverso.put("&lnsim;",'⋦');
    	mapaInverso.put("&gnsim;",'⋧');
    	mapaInverso.put("&prnsim;",'⋨');
    	mapaInverso.put("&scnsim;",'⋩');
    	mapaInverso.put("&nltri;",'⋪');
    	mapaInverso.put("&nrtri;",'⋫');
    	mapaInverso.put("&nltrie;",'⋬');
    	mapaInverso.put("&nrtrie;",'⋭');
    	mapaInverso.put("&vellip;",'⋮');
    	mapaInverso.put("&ctdot;",'⋯');
    	mapaInverso.put("&utdot;",'⋰');
    	mapaInverso.put("&dtdot;",'⋱');
    	mapaInverso.put("&disin;",'⋲');
    	mapaInverso.put("&isinsv;",'⋳');
    	mapaInverso.put("&isins;",'⋴');
    	mapaInverso.put("&isindot;",'⋵');
    	mapaInverso.put("&notinvc;",'⋶');
    	mapaInverso.put("&notinvb;",'⋷');
    	mapaInverso.put("&isinE;",'⋹');
    	mapaInverso.put("&nisd;",'⋺');
    	mapaInverso.put("&xnis;",'⋻');
    	mapaInverso.put("&nis;",'⋼');
    	mapaInverso.put("&notnivc;",'⋽');
    	mapaInverso.put("&notnivb;",'⋾');
    	mapaInverso.put("&barwed;",'⌅');
    	mapaInverso.put("&Barwed;",'⌆');
    	mapaInverso.put("&lceil;",'⌈');
    	mapaInverso.put("&rceil;",'⌉');
    	mapaInverso.put("&lfloor;",'⌊');
    	mapaInverso.put("&rfloor;",'⌋');
    	mapaInverso.put("&drcrop;",'⌌');
    	mapaInverso.put("&dlcrop;",'⌍');
    	mapaInverso.put("&urcrop;",'⌎');
    	mapaInverso.put("&ulcrop;",'⌏');
    	mapaInverso.put("&bnot;",'⌐');
    	mapaInverso.put("&profline;",'⌒');
    	mapaInverso.put("&profsurf;",'⌓');
    	mapaInverso.put("&telrec;",'⌕');
    	mapaInverso.put("&target;",'⌖');
    	mapaInverso.put("&ulcorn;",'⌜');
    	mapaInverso.put("&urcorn;",'⌝');
    	mapaInverso.put("&dlcorn;",'⌞');
    	mapaInverso.put("&drcorn;",'⌟');
    	mapaInverso.put("&frown;",'⌢');
    	mapaInverso.put("&smile;",'⌣');
    	mapaInverso.put("&cylcty;",'⌭');
    	mapaInverso.put("&profalar;",'⌮');
    	mapaInverso.put("&topbot;",'⌶');
    	mapaInverso.put("&ovbar;",'⌽');
    	mapaInverso.put("&solbar;",'⌿');
    	mapaInverso.put("&angzarr;",'⍼');
    	mapaInverso.put("&lmoust;",'⎰');
    	mapaInverso.put("&rmoust;",'⎱');
    	mapaInverso.put("&tbrk;",'⎴');
    	mapaInverso.put("&bbrk;",'⎵');
    	mapaInverso.put("&bbrktbrk;",'⎶');
    	mapaInverso.put("&OverParenthesis;",'⏜');
    	mapaInverso.put("&UnderParenthesis;",'⏝');
    	mapaInverso.put("&OverBrace;",'⏞');
    	mapaInverso.put("&UnderBrace;",'⏟');
    	mapaInverso.put("&trpezium;",'⏢');
    	mapaInverso.put("&elinters;",'⏧');
    	mapaInverso.put("&blank;",'␣');
    	mapaInverso.put("&oS;",'Ⓢ');
    	mapaInverso.put("&boxh;",'─');
    	mapaInverso.put("&boxv;",'│');
    	mapaInverso.put("&boxdr;",'┌');
    	mapaInverso.put("&boxdl;",'┐');
    	mapaInverso.put("&boxur;",'└');
    	mapaInverso.put("&boxul;",'┘');
    	mapaInverso.put("&boxvr;",'├');
    	mapaInverso.put("&boxvl;",'┤');
    	mapaInverso.put("&boxhd;",'┬');
    	mapaInverso.put("&boxhu;",'┴');
    	mapaInverso.put("&boxvh;",'┼');
    	mapaInverso.put("&boxH;",'═');
    	mapaInverso.put("&boxV;",'║');
    	mapaInverso.put("&boxdR;",'╒');
    	mapaInverso.put("&boxDr;",'╓');
    	mapaInverso.put("&boxDR;",'╔');
    	mapaInverso.put("&boxdL;",'╕');
    	mapaInverso.put("&boxDl;",'╖');
    	mapaInverso.put("&boxDL;",'╗');
    	mapaInverso.put("&boxuR;",'╘');
    	mapaInverso.put("&boxUr;",'╙');
    	mapaInverso.put("&boxUR;",'╚');
    	mapaInverso.put("&boxuL;",'╛');
    	mapaInverso.put("&boxUl;",'╜');
    	mapaInverso.put("&boxUL;",'╝');
    	mapaInverso.put("&boxvR;",'╞');
    	mapaInverso.put("&boxVr;",'╟');
    	mapaInverso.put("&boxVR;",'╠');
    	mapaInverso.put("&boxvL;",'╡');
    	mapaInverso.put("&boxVl;",'╢');
    	mapaInverso.put("&boxVL;",'╣');
    	mapaInverso.put("&boxHd;",'╤');
    	mapaInverso.put("&boxhD;",'╥');
    	mapaInverso.put("&boxHD;",'╦');
    	mapaInverso.put("&boxHu;",'╧');
    	mapaInverso.put("&boxhU;",'╨');
    	mapaInverso.put("&boxHU;",'╩');
    	mapaInverso.put("&boxvH;",'╪');
    	mapaInverso.put("&boxVh;",'╫');
    	mapaInverso.put("&boxVH;",'╬');
    	mapaInverso.put("&uhblk;",'▀');
    	mapaInverso.put("&lhblk;",'▄');
    	mapaInverso.put("&block;",'█');
    	mapaInverso.put("&blk14;",'░');
    	mapaInverso.put("&blk12;",'▒');
    	mapaInverso.put("&blk34;",'▓');
    	mapaInverso.put("&squ;",'□');
    	mapaInverso.put("&squf;",'▪');
    	mapaInverso.put("&EmptyVerySmallSquare;",'▫');
    	mapaInverso.put("&rect;",'▭');
    	mapaInverso.put("&marker;",'▮');
    	mapaInverso.put("&fltns;",'▱');
    	mapaInverso.put("&xutri;",'△');
    	mapaInverso.put("&utrif;",'▴');
    	mapaInverso.put("&utri;",'▵');
    	mapaInverso.put("&rtrif;",'▸');
    	mapaInverso.put("&rtri;",'▹');
    	mapaInverso.put("&xdtri;",'▽');
    	mapaInverso.put("&dtrif;",'▾');
    	mapaInverso.put("&dtri;",'▿');
    	mapaInverso.put("&ltrif;",'◂');
    	mapaInverso.put("&ltri;",'◃');
    	mapaInverso.put("&loz;",'◊');
    	mapaInverso.put("&cir;",'○');
    	mapaInverso.put("&tridot;",'◬');
    	mapaInverso.put("&xcirc;",'◯');
    	mapaInverso.put("&ultri;",'◸');
    	mapaInverso.put("&urtri;",'◹');
    	mapaInverso.put("&lltri;",'◺');
    	mapaInverso.put("&EmptySmallSquare;",'◻');
    	mapaInverso.put("&FilledSmallSquare;",'◼');
    	mapaInverso.put("&starf;",'★');
    	mapaInverso.put("&star;",'☆');
    	mapaInverso.put("&phone;",'☎');
    	mapaInverso.put("&female;",'♀');
    	mapaInverso.put("&male;",'♂');
    	mapaInverso.put("&spades;",'♠');
    	mapaInverso.put("&clubs;",'♣');
    	mapaInverso.put("&hearts;",'♥');
    	mapaInverso.put("&diams;",'♦');
    	mapaInverso.put("&sung;",'♪');
    	mapaInverso.put("&flat;",'♭');
    	mapaInverso.put("&natur;",'♮');
    	mapaInverso.put("&sharp;",'♯');
    	mapaInverso.put("&check;",'✓');
    	mapaInverso.put("&cross;",'✗');
    	mapaInverso.put("&malt;",'✠');
    	mapaInverso.put("&sext;",'✶');
    	mapaInverso.put("&VerticalSeparator;",'❘');
    	mapaInverso.put("&lbbrk;",'❲');
    	mapaInverso.put("&rbbrk;",'❳');
    	mapaInverso.put("&lobrk;",'⟦');
    	mapaInverso.put("&robrk;",'⟧');
    	mapaInverso.put("&lang;",'⟨');
    	mapaInverso.put("&rang;",'⟩');
    	mapaInverso.put("&Lang;",'⟪');
    	mapaInverso.put("&Rang;",'⟫');
    	mapaInverso.put("&loang;",'⟬');
    	mapaInverso.put("&roang;",'⟭');
    	mapaInverso.put("&xlarr;",'⟵');
    	mapaInverso.put("&xrarr;",'⟶');
    	mapaInverso.put("&xharr;",'⟷');
    	mapaInverso.put("&xlArr;",'⟸');
    	mapaInverso.put("&xrArr;",'⟹');
    	mapaInverso.put("&xhArr;",'⟺');
    	mapaInverso.put("&xmap;",'⟼');
    	mapaInverso.put("&dzigrarr;",'⟿');
    	mapaInverso.put("&nvlArr;",'⤂');
    	mapaInverso.put("&nvrArr;",'⤃');
    	mapaInverso.put("&nvHarr;",'⤄');
    	mapaInverso.put("&Map;",'⤅');
    	mapaInverso.put("&lbarr;",'⤌');
    	mapaInverso.put("&rbarr;",'⤍');
    	mapaInverso.put("&lBarr;",'⤎');
    	mapaInverso.put("&rBarr;",'⤏');
    	mapaInverso.put("&RBarr;",'⤐');
    	mapaInverso.put("&DDotrahd;",'⤑');
    	mapaInverso.put("&UpArrowBar;",'⤒');
    	mapaInverso.put("&DownArrowBar;",'⤓');
    	mapaInverso.put("&Rarrtl;",'⤖');
    	mapaInverso.put("&latail;",'⤙');
    	mapaInverso.put("&ratail;",'⤚');
    	mapaInverso.put("&lAtail;",'⤛');
    	mapaInverso.put("&rAtail;",'⤜');
    	mapaInverso.put("&larrfs;",'⤝');
    	mapaInverso.put("&rarrfs;",'⤞');
    	mapaInverso.put("&larrbfs;",'⤟');
    	mapaInverso.put("&rarrbfs;",'⤠');
    	mapaInverso.put("&nwarhk;",'⤣');
    	mapaInverso.put("&nearhk;",'⤤');
    	mapaInverso.put("&searhk;",'⤥');
    	mapaInverso.put("&swarhk;",'⤦');
    	mapaInverso.put("&nwnear;",'⤧');
    	mapaInverso.put("&nesear;",'⤨');
    	mapaInverso.put("&seswar;",'⤩');
    	mapaInverso.put("&swnwar;",'⤪');
    	mapaInverso.put("&rarrc;",'⤳');
    	mapaInverso.put("&cudarrr;",'⤵');
    	mapaInverso.put("&ldca;",'⤶');
    	mapaInverso.put("&rdca;",'⤷');
    	mapaInverso.put("&cudarrl;",'⤸');
    	mapaInverso.put("&larrpl;",'⤹');
    	mapaInverso.put("&curarrm;",'⤼');
    	mapaInverso.put("&cularrp;",'⤽');
    	mapaInverso.put("&rarrpl;",'⥅');
    	mapaInverso.put("&harrcir;",'⥈');
    	mapaInverso.put("&Uarrocir;",'⥉');
    	mapaInverso.put("&lurdshar;",'⥊');
    	mapaInverso.put("&ldrushar;",'⥋');
    	mapaInverso.put("&LeftRightVector;",'⥎');
    	mapaInverso.put("&RightUpDownVector;",'⥏');
    	mapaInverso.put("&DownLeftRightVector;",'⥐');
    	mapaInverso.put("&LeftUpDownVector;",'⥑');
    	mapaInverso.put("&LeftVectorBar;",'⥒');
    	mapaInverso.put("&RightVectorBar;",'⥓');
    	mapaInverso.put("&RightUpVectorBar;",'⥔');
    	mapaInverso.put("&RightDownVectorBar;",'⥕');
    	mapaInverso.put("&DownLeftVectorBar;",'⥖');
    	mapaInverso.put("&DownRightVectorBar;",'⥗');
    	mapaInverso.put("&LeftUpVectorBar;",'⥘');
    	mapaInverso.put("&LeftDownVectorBar;",'⥙');
    	mapaInverso.put("&LeftTeeVector;",'⥚');
    	mapaInverso.put("&RightTeeVector;",'⥛');
    	mapaInverso.put("&RightUpTeeVector;",'⥜');
    	mapaInverso.put("&RightDownTeeVector;",'⥝');
    	mapaInverso.put("&DownLeftTeeVector;",'⥞');
    	mapaInverso.put("&DownRightTeeVector;",'⥟');
    	mapaInverso.put("&LeftUpTeeVector;",'⥠');
    	mapaInverso.put("&LeftDownTeeVector;",'⥡');
    	mapaInverso.put("&lHar;",'⥢');
    	mapaInverso.put("&uHar;",'⥣');
    	mapaInverso.put("&rHar;",'⥤');
    	mapaInverso.put("&dHar;",'⥥');
    	mapaInverso.put("&luruhar;",'⥦');
    	mapaInverso.put("&ldrdhar;",'⥧');
    	mapaInverso.put("&ruluhar;",'⥨');
    	mapaInverso.put("&rdldhar;",'⥩');
    	mapaInverso.put("&lharul;",'⥪');
    	mapaInverso.put("&llhard;",'⥫');
    	mapaInverso.put("&rharul;",'⥬');
    	mapaInverso.put("&lrhard;",'⥭');
    	mapaInverso.put("&udhar;",'⥮');
    	mapaInverso.put("&duhar;",'⥯');
    	mapaInverso.put("&RoundImplies;",'⥰');
    	mapaInverso.put("&erarr;",'⥱');
    	mapaInverso.put("&simrarr;",'⥲');
    	mapaInverso.put("&larrsim;",'⥳');
    	mapaInverso.put("&rarrsim;",'⥴');
    	mapaInverso.put("&rarrap;",'⥵');
    	mapaInverso.put("&ltlarr;",'⥶');
    	mapaInverso.put("&gtrarr;",'⥸');
    	mapaInverso.put("&subrarr;",'⥹');
    	mapaInverso.put("&suplarr;",'⥻');
    	mapaInverso.put("&lfisht;",'⥼');
    	mapaInverso.put("&rfisht;",'⥽');
    	mapaInverso.put("&ufisht;",'⥾');
    	mapaInverso.put("&dfisht;",'⥿');
    	mapaInverso.put("&lopar;",'⦅');
    	mapaInverso.put("&ropar;",'⦆');
    	mapaInverso.put("&lbrke;",'⦋');
    	mapaInverso.put("&rbrke;",'⦌');
    	mapaInverso.put("&lbrkslu;",'⦍');
    	mapaInverso.put("&rbrksld;",'⦎');
    	mapaInverso.put("&lbrksld;",'⦏');
    	mapaInverso.put("&rbrkslu;",'⦐');
    	mapaInverso.put("&langd;",'⦑');
    	mapaInverso.put("&rangd;",'⦒');
    	mapaInverso.put("&lparlt;",'⦓');
    	mapaInverso.put("&rpargt;",'⦔');
    	mapaInverso.put("&gtlPar;",'⦕');
    	mapaInverso.put("&ltrPar;",'⦖');
    	mapaInverso.put("&vzigzag;",'⦚');
    	mapaInverso.put("&vangrt;",'⦜');
    	mapaInverso.put("&angrtvbd;",'⦝');
    	mapaInverso.put("&ange;",'⦤');
    	mapaInverso.put("&range;",'⦥');
    	mapaInverso.put("&dwangle;",'⦦');
    	mapaInverso.put("&uwangle;",'⦧');
    	mapaInverso.put("&angmsdaa;",'⦨');
    	mapaInverso.put("&angmsdab;",'⦩');
    	mapaInverso.put("&angmsdac;",'⦪');
    	mapaInverso.put("&angmsdad;",'⦫');
    	mapaInverso.put("&angmsdae;",'⦬');
    	mapaInverso.put("&angmsdaf;",'⦭');
    	mapaInverso.put("&angmsdag;",'⦮');
    	mapaInverso.put("&angmsdah;",'⦯');
    	mapaInverso.put("&bemptyv;",'⦰');
    	mapaInverso.put("&demptyv;",'⦱');
    	mapaInverso.put("&cemptyv;",'⦲');
    	mapaInverso.put("&raemptyv;",'⦳');
    	mapaInverso.put("&laemptyv;",'⦴');
    	mapaInverso.put("&ohbar;",'⦵');
    	mapaInverso.put("&omid;",'⦶');
    	mapaInverso.put("&opar;",'⦷');
    	mapaInverso.put("&operp;",'⦹');
    	mapaInverso.put("&olcross;",'⦻');
    	mapaInverso.put("&odsold;",'⦼');
    	mapaInverso.put("&olcir;",'⦾');
    	mapaInverso.put("&ofcir;",'⦿');
    	mapaInverso.put("&olt;",'⧀');
    	mapaInverso.put("&ogt;",'⧁');
    	mapaInverso.put("&cirscir;",'⧂');
    	mapaInverso.put("&cirE;",'⧃');
    	mapaInverso.put("&solb;",'⧄');
    	mapaInverso.put("&bsolb;",'⧅');
    	mapaInverso.put("&boxbox;",'⧉');
    	mapaInverso.put("&trisb;",'⧍');
    	mapaInverso.put("&rtriltri;",'⧎');
    	mapaInverso.put("&LeftTriangleBar;",'⧏');
    	mapaInverso.put("&RightTriangleBar;",'⧐');
    	mapaInverso.put("&race;",'⧚');
    	mapaInverso.put("&iinfin;",'⧜');
    	mapaInverso.put("&infintie;",'⧝');
    	mapaInverso.put("&nvinfin;",'⧞');
    	mapaInverso.put("&eparsl;",'⧣');
    	mapaInverso.put("&smeparsl;",'⧤');
    	mapaInverso.put("&eqvparsl;",'⧥');
    	mapaInverso.put("&lozf;",'⧫');
    	mapaInverso.put("&RuleDelayed;",'⧴');
    	mapaInverso.put("&dsol;",'⧶');
    	mapaInverso.put("&xodot;",'⨀');
    	mapaInverso.put("&xoplus;",'⨁');
    	mapaInverso.put("&xotime;",'⨂');
    	mapaInverso.put("&xuplus;",'⨄');
    	mapaInverso.put("&xsqcup;",'⨆');
    	mapaInverso.put("&qint;",'⨌');
    	mapaInverso.put("&fpartint;",'⨍');
    	mapaInverso.put("&cirfnint;",'⨐');
    	mapaInverso.put("&awint;",'⨑');
    	mapaInverso.put("&rppolint;",'⨒');
    	mapaInverso.put("&scpolint;",'⨓');
    	mapaInverso.put("&npolint;",'⨔');
    	mapaInverso.put("&pointint;",'⨕');
    	mapaInverso.put("&quatint;",'⨖');
    	mapaInverso.put("&intlarhk;",'⨗');
    	mapaInverso.put("&pluscir;",'⨢');
    	mapaInverso.put("&plusacir;",'⨣');
    	mapaInverso.put("&simplus;",'⨤');
    	mapaInverso.put("&plusdu;",'⨥');
    	mapaInverso.put("&plussim;",'⨦');
    	mapaInverso.put("&plustwo;",'⨧');
    	mapaInverso.put("&mcomma;",'⨩');
    	mapaInverso.put("&minusdu;",'⨪');
    	mapaInverso.put("&loplus;",'⨭');
    	mapaInverso.put("&roplus;",'⨮');
    	mapaInverso.put("&Cross;",'⨯');
    	mapaInverso.put("&timesd;",'⨰');
    	mapaInverso.put("&timesbar;",'⨱');
    	mapaInverso.put("&smashp;",'⨳');
    	mapaInverso.put("&lotimes;",'⨴');
    	mapaInverso.put("&rotimes;",'⨵');
    	mapaInverso.put("&otimesas;",'⨶');
    	mapaInverso.put("&Otimes;",'⨷');
    	mapaInverso.put("&odiv;",'⨸');
    	mapaInverso.put("&triplus;",'⨹');
    	mapaInverso.put("&triminus;",'⨺');
    	mapaInverso.put("&tritime;",'⨻');
    	mapaInverso.put("&iprod;",'⨼');
    	mapaInverso.put("&amalg;",'⨿');
    	mapaInverso.put("&capdot;",'⩀');
    	mapaInverso.put("&ncup;",'⩂');
    	mapaInverso.put("&ncap;",'⩃');
    	mapaInverso.put("&capand;",'⩄');
    	mapaInverso.put("&cupor;",'⩅');
    	mapaInverso.put("&cupcap;",'⩆');
    	mapaInverso.put("&capcup;",'⩇');
    	mapaInverso.put("&cupbrcap;",'⩈');
    	mapaInverso.put("&capbrcup;",'⩉');
    	mapaInverso.put("&cupcup;",'⩊');
    	mapaInverso.put("&capcap;",'⩋');
    	mapaInverso.put("&ccups;",'⩌');
    	mapaInverso.put("&ccaps;",'⩍');
    	mapaInverso.put("&ccupssm;",'⩐');
    	mapaInverso.put("&And;",'⩓');
    	mapaInverso.put("&Or;",'⩔');
    	mapaInverso.put("&andand;",'⩕');
    	mapaInverso.put("&oror;",'⩖');
    	mapaInverso.put("&orslope;",'⩗');
    	mapaInverso.put("&andslope;",'⩘');
    	mapaInverso.put("&andv;",'⩚');
    	mapaInverso.put("&orv;",'⩛');
    	mapaInverso.put("&andd;",'⩜');
    	mapaInverso.put("&ord;",'⩝');
    	mapaInverso.put("&wedbar;",'⩟');
    	mapaInverso.put("&sdote;",'⩦');
    	mapaInverso.put("&simdot;",'⩪');
    	mapaInverso.put("&congdot;",'⩭');
    	mapaInverso.put("&easter;",'⩮');
    	mapaInverso.put("&apacir;",'⩯');
    	mapaInverso.put("&apE;",'⩰');
    	mapaInverso.put("&eplus;",'⩱');
    	mapaInverso.put("&pluse;",'⩲');
    	mapaInverso.put("&Esim;",'⩳');
    	mapaInverso.put("&Colone;",'⩴');
    	mapaInverso.put("&Equal;",'⩵');
    	mapaInverso.put("&eDDot;",'⩷');
    	mapaInverso.put("&equivDD;",'⩸');
    	mapaInverso.put("&ltcir;",'⩹');
    	mapaInverso.put("&gtcir;",'⩺');
    	mapaInverso.put("&ltquest;",'⩻');
    	mapaInverso.put("&gtquest;",'⩼');
    	mapaInverso.put("&les;",'⩽');
    	mapaInverso.put("&ges;",'⩾');
    	mapaInverso.put("&lesdot;",'⩿');
    	mapaInverso.put("&gesdot;",'⪀');
    	mapaInverso.put("&lesdoto;",'⪁');
    	mapaInverso.put("&gesdoto;",'⪂');
    	mapaInverso.put("&lesdotor;",'⪃');
    	mapaInverso.put("&gesdotol;",'⪄');
    	mapaInverso.put("&lap;",'⪅');
    	mapaInverso.put("&gap;",'⪆');
    	mapaInverso.put("&lne;",'⪇');
    	mapaInverso.put("&gne;",'⪈');
    	mapaInverso.put("&lnap;",'⪉');
    	mapaInverso.put("&gnap;",'⪊');
    	mapaInverso.put("&lEg;",'⪋');
    	mapaInverso.put("&gEl;",'⪌');
    	mapaInverso.put("&lsime;",'⪍');
    	mapaInverso.put("&gsime;",'⪎');
    	mapaInverso.put("&lsimg;",'⪏');
    	mapaInverso.put("&gsiml;",'⪐');
    	mapaInverso.put("&lgE;",'⪑');
    	mapaInverso.put("&glE;",'⪒');
    	mapaInverso.put("&lesges;",'⪓');
    	mapaInverso.put("&gesles;",'⪔');
    	mapaInverso.put("&els;",'⪕');
    	mapaInverso.put("&egs;",'⪖');
    	mapaInverso.put("&elsdot;",'⪗');
    	mapaInverso.put("&egsdot;",'⪘');
    	mapaInverso.put("&el;",'⪙');
    	mapaInverso.put("&eg;",'⪚');
    	mapaInverso.put("&siml;",'⪝');
    	mapaInverso.put("&simg;",'⪞');
    	mapaInverso.put("&simlE;",'⪟');
    	mapaInverso.put("&simgE;",'⪠');
    	mapaInverso.put("&LessLess;",'⪡');
    	mapaInverso.put("&GreaterGreater;",'⪢');
    	mapaInverso.put("&glj;",'⪤');
    	mapaInverso.put("&gla;",'⪥');
    	mapaInverso.put("&ltcc;",'⪦');
    	mapaInverso.put("&gtcc;",'⪧');
    	mapaInverso.put("&lescc;",'⪨');
    	mapaInverso.put("&gescc;",'⪩');
    	mapaInverso.put("&smt;",'⪪');
    	mapaInverso.put("&lat;",'⪫');
    	mapaInverso.put("&smte;",'⪬');
    	mapaInverso.put("&late;",'⪭');
    	mapaInverso.put("&bumpE;",'⪮');
    	mapaInverso.put("&pre;",'⪯');
    	mapaInverso.put("&sce;",'⪰');
    	mapaInverso.put("&prE;",'⪳');
    	mapaInverso.put("&scE;",'⪴');
    	mapaInverso.put("&prnE;",'⪵');
    	mapaInverso.put("&scnE;",'⪶');
    	mapaInverso.put("&prap;",'⪷');
    	mapaInverso.put("&scap;",'⪸');
    	mapaInverso.put("&prnap;",'⪹');
    	mapaInverso.put("&scnap;",'⪺');
    	mapaInverso.put("&Pr;",'⪻');
    	mapaInverso.put("&Sc;",'⪼');
    	mapaInverso.put("&subdot;",'⪽');
    	mapaInverso.put("&supdot;",'⪾');
    	mapaInverso.put("&subplus;",'⪿');
    	mapaInverso.put("&supplus;",'⫀');
    	mapaInverso.put("&submult;",'⫁');
    	mapaInverso.put("&supmult;",'⫂');
    	mapaInverso.put("&subedot;",'⫃');
    	mapaInverso.put("&supedot;",'⫄');
    	mapaInverso.put("&subE;",'⫅');
    	mapaInverso.put("&supE;",'⫆');
    	mapaInverso.put("&subsim;",'⫇');
    	mapaInverso.put("&supsim;",'⫈');
    	mapaInverso.put("&subnE;",'⫋');
    	mapaInverso.put("&supnE;",'⫌');
    	mapaInverso.put("&csub;",'⫏');
    	mapaInverso.put("&csup;",'⫐');
    	mapaInverso.put("&csube;",'⫑');
    	mapaInverso.put("&csupe;",'⫒');
    	mapaInverso.put("&subsup;",'⫓');
    	mapaInverso.put("&supsub;",'⫔');
    	mapaInverso.put("&subsub;",'⫕');
    	mapaInverso.put("&supsup;",'⫖');
    	mapaInverso.put("&suphsub;",'⫗');
    	mapaInverso.put("&supdsub;",'⫘');
    	mapaInverso.put("&forkv;",'⫙');
    	mapaInverso.put("&topfork;",'⫚');
    	mapaInverso.put("&mlcp;",'⫛');
    	mapaInverso.put("&Dashv;",'⫤');
    	mapaInverso.put("&Vdashl;",'⫦');
    	mapaInverso.put("&Barv;",'⫧');
    	mapaInverso.put("&vBar;",'⫨');
    	mapaInverso.put("&vBarv;",'⫩');
    	mapaInverso.put("&Vbar;",'⫫');
    	mapaInverso.put("&Not;",'⫬');
    	mapaInverso.put("&bNot;",'⫭');
    	mapaInverso.put("&rnmid;",'⫮');
    	mapaInverso.put("&cirmid;",'⫯');
    	mapaInverso.put("&midcir;",'⫰');
    	mapaInverso.put("&topcir;",'⫱');
    	mapaInverso.put("&nhpar;",'⫲');
    	mapaInverso.put("&parsim;",'⫳');
    	mapaInverso.put("&parsl;",'⫽');
    	mapaInverso.put("&fflig;",'ﬀ');
    	mapaInverso.put("&filig;",'ﬁ');
    	mapaInverso.put("&fllig;",'ﬂ');
    	mapaInverso.put("&ffilig;",'ﬃ');
    	mapaInverso.put("&ffllig;",'ﬄ');
    	cedenaVuelta=mapaInverso.get(cadena).toString();
    	return cedenaVuelta;
	}
	public static void geneMeta(String separadorOriginal, String src, String title, String author, String subject, String keywords, String dest, String anyo) {
		try {
			
			/*XmpWriter xmp = new XmpWriter(os);
			XmpSchema dc = new com.itextpdf.text.xml.xmp.DublinCoreSchema();
			XmpArray ssubject = new XmpArray(XmpArray.UNORDERED);*/
			PdfDocument pdfDoc =
			    new PdfDocument(new PdfReader(src), new PdfWriter(dest));
			com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDoc);
			subject=subject.replaceAll("()", "");
			PdfDocumentInfo info = pdfDoc.getDocumentInfo();
		    info.setTitle(title);
		    //info.setAuthor(author);
		    info.setSubject(subject);
		    info.setKeywords(keywords);
		    info.setCreator("Colegio Oficial de la Psicología de Madrid");
		   
			
			XMPMeta xmpMeta = XMPMetaFactory.create();
			xmpMeta.setLocalizedText(XMPConst.NS_DC, DublinCoreProperties.TITLE, XMPConst.X_DEFAULT, XMPConst.X_DEFAULT, title);
			xmpMeta.setProperty(XMPConst.NS_PDF, PdfProperties.KEYWORDS, keywords);
			xmpMeta.setProperty(XMPConst.NS_PDF, "Subject", subject);
			
			String copyrightName = "This is an open access article under the CC BY-NC-ND license(c) "+anyo+" Colegio Oficial de la Psicología de Madrid";
			String copyrightUrl = "https://creativecommons.org/licenses/by-nc-nd/4.0/deed.es_ES";
			
			xmpMeta.setProperty(XMPConst.NS_DC, DublinCoreProperties.RIGHTS, copyrightName);
			xmpMeta.setProperty(XMPConst.NS_XMP_RIGHTS, "Marked", "true");
			XMPMeta extMeta = XMPMetaFactory.parseFromString("<rdf:RDF xmlns:rdf=\"" + XMPConst.NS_RDF + "\"><rdf:Description rdf:about=\"\" xmlns:xmpRights=\"http://ns.adobe.com/xap/1.0/rights/\"><xmpRights:Marked>True</xmpRights:Marked><xmpRights:WebStatement>https://creativecommons.org/licenses/by-nc-nd/4.0/deed.es_ES</xmpRights:WebStatement></rdf:Description></rdf:RDF>");
		    XMPUtils.appendProperties(extMeta, xmpMeta, true, true);
		    
		    
			
		    String autores="";
			
			/*miramos primero si contienen # o $ porque esos son antiguos y se formatea como apellidos,nombre*/
			if(author.indexOf("#")!=-1 || author.indexOf("$")!=-1 || separadorOriginal!=null){
				String author1[];
				String separador="";
				if(author.indexOf("#")!=-1){
					separador="#";
					author=author.replaceAll(" and ", separador+" ");
					author=author.replaceAll(" and, ", separador+" ");
					author=author.replaceAll(" y ", separador+" ");
					author=author.replaceAll(" y, ", separador+" ");
					author1 = author.split("#");
					
				}else{
					separador="$";
					author=author.replaceAll(" and ", separador+" ");
					author=author.replaceAll(" and, ", separador+" ");
					author=author.replaceAll(" y ", separador+" ");
					author=author.replaceAll(" y, ", separador+" ");
					author1 = author.split("\\$");
					
				}
				
				for (int j=0; j<author1.length; j++){
					if(author1[j].indexOf(",")!=-1){
						/*viene como apellidos, nombre+*/
						String[] nombs=author1[j].split(",");
						autores=autores+nombs[1].trim()+ " " +nombs[0].trim()+", ";
					}else{
						/*viene todo junto*/
						autores=autores+author1[j].trim()+", ";
					}
				}
			}
			else{
				author=author.replaceAll(" and, ", ", ");
				author=author.replaceAll(" and , ", ", ");
				author=author.replaceAll(" ,and ", ", ");
				author=author.replaceAll(" , and ", ", ");
				author=author.replaceAll(" and ", ", ");
				author=author.replaceAll(" y, ", ", ");
				author=author.replaceAll(" y , ", ", ");
				author=author.replaceAll(" ,y ", ", ");
				author=author.replaceAll(" , y ", ", ");
				author=author.replaceAll(" y ", ", ");
				author=author.replaceAll(",,", ",");
				String author1[] = author.split(",");
				for (int j=0; j<author1.length; j++){
					autores=autores+author1[j].trim()+", ";
				}
			}
			if(autores.indexOf(", ")!=-1)
				autores=autores.substring(0, autores.lastIndexOf(", "));
			
			
			
			String sAutores[] = autores.split(",");
			for (int i=0; i<sAutores.length;i++){
				DublinCoreProperties.addAuthor( xmpMeta, sAutores[i]);
			}
			
			pdfDoc.setXmpMetadata(xmpMeta);
			
			
			pdfDoc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
