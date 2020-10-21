package codigo.cfg;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import codigo.db.ConfigDB;

public class Config { 
	
	private static final String CONFIG_FILE = "config.xml";
	private Document configFile;
	private static Config instance;

	private Config() {
		this.checkConfigFile();
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}
	 
	
	private void checkConfigFile(){
		File file = new File(CONFIG_FILE);
		if (!file.exists()) { 
			// Creamos la Config
			configFile = createDocument(file);
		}
		// Cargamos la Config
		configFile = loadDocument(file);
	}
	
	private Document loadDocument(File file) { 
		
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(file);
			  
			  
			  NodeList nList = doc.getElementsByTagName("conexion");
			  if (nList.getLength() > 0) {
				  Node data = nList.item(0);
				  String host = "";
				  String user = "";
				  String pass = "";
				  if (data.hasChildNodes()) {
					  NodeList childs = data.getChildNodes();
					  for (int idx = 0; idx < childs.getLength(); idx++) {
						  Node item = childs.item(idx); 
						  String name = item.getNodeName();
						  if (name.equals("host")) {
							  host = item.getTextContent();
						  }
						  if (name.equals("user")) {
							  user = item.getTextContent();		  
						  }
						  if (name.equals("pass")) {
							  pass = item.getTextContent();
						  }
					  }
				  } 
				  ConfigDB.setDBConexion(host, user, pass);
			  }
			  
			  nList = doc.getElementsByTagName("queries");
			  if (nList.getLength() > 0) {
				  Node data = nList.item(0);
				  if (data.hasChildNodes()) {
					  NodeList childs = data.getChildNodes();
					  for (int idx = 0; idx < childs.getLength(); idx++) {
						  Node item = childs.item(idx);
						  String query = item.getTextContent();
						  query = query.replaceAll("\n", "").replaceAll("\t", "").trim();
						  if(!query.isEmpty()) {
							  ConfigDB.addQuery( query );  
						  }
					  }
				  }
			  }
			  
			  
			  // getTextContent
			  
			} catch(Exception e) {
			  e.printStackTrace();
			}
		
		return null;
	}

	private Document createDocument(File file) {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.newDocument();
			
			  // =============================== 
			  Element config = doc.createElement("configuration");
			  doc.appendChild(config);		
			  
			  Element conex = doc.createElement("conexion");
			  config.appendChild(conex);
			  Element host = doc.createElement("host");
			  host.appendChild(doc.createTextNode("jdbc:mysql://localhost:3306/accesodatos1"));
			  conex.appendChild(host);
			  
			  Element user = doc.createElement("user");
			  user.appendChild(doc.createTextNode("root"));
			  conex.appendChild(user);
			  
			  Element pass = doc.createElement("pass");
			  pass.appendChild(doc.createTextNode("1234"));
			  conex.appendChild(pass);
			  
			  Element queries = doc.createElement("queries");
			  config.appendChild(queries);		
			  
			  Element query01 = doc.createElement("query");
			  query01.appendChild(doc.createTextNode("CREATE TABLE IF NOT EXISTS `person` ("
						+ "	`id`	int PRIMARY KEY AUTO_INCREMENT,"
						+ "	`name`	varchar(20),"
						+ "	`lastName`	varchar(20));"));
			  queries.appendChild(query01);	  
			  
			  Element query02 = doc.createElement("query");
			  query02.appendChild(doc.createTextNode("CREATE TABLE IF NOT EXISTS `producto` "
			  		+ "(	`id` int PRIMARY KEY AUTO_INCREMENT, "
			  		+ "`name` varchar(20),"
			  		+ "	`desc`	int, `precio` int DEFAULT 0, "
			  		+ "`stock`	int DEFAULT 1);"));
			  queries.appendChild(query02);	   
			  
			  Element query03 = doc.createElement("query");
			  query03.appendChild(doc.createTextNode("CREATE TABLE IF NOT EXISTS "
			  		+ "`usuario` (`id` int PRIMARY KEY AUTO_INCREMENT, "
			  		+ "`user` varchar(25),"
			  		+ "`password` varchar(20));"));
			  queries.appendChild(query03);
			   
			  
			  // ===============================
			  
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  
			  
			  StreamResult result = new StreamResult(file);		 

			  transformer.transform(source, result);
			   
			  System.out.println(file.getAbsolutePath());
			  
			  System.out.println("FILE EXISTS: "+file.exists());
			  
			  return doc;
		}catch(Exception e) {
			System.err.println(e.getMessage());
		} 
		return null;
	}
}
