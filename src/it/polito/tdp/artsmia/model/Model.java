package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.sun.javafx.geom.Edge;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	SimpleWeightedGraph<Integer, DefaultWeightedEdge> grafo;
	List<ArtObject> listObject;
	ArtsmiaDAO dao = new ArtsmiaDAO();
	public List<Collegamento> collegamenti;
	List<Integer> listaIds;
	int countRiusciti=0;
	
	
	public ArtObject cercaOgg(int id) {
		for(ArtObject ao: listObject) {
			if(ao.getId()==id)
				return ao;
		}
		return null;
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		listObject = dao.listObjects();
		collegamenti = dao.listCollegamenti();
		listaIds = new ArrayList<Integer>();
		for(ArtObject ao: listObject) {
			listaIds.add(ao.getId());
		}
//		try {
		Graphs.addAllVertices(this.grafo, listaIds);
		// Ora aggiungo gli edges
		
		for(Collegamento c: collegamenti) {
			
			System.out.println("riuscito" + countRiusciti);
			countRiusciti++;
			if(countRiusciti==99457) {
				System.out.println(collegamenti.size());
				String stopDebug="";
				DefaultWeightedEdge e = grafo.getEdge(c.objId1, c.objId2);
				if(grafo.vertexSet().contains(c.objId1)) {
					String stopDebug2 = "";
				}
				if(grafo.vertexSet().contains(c.objId2)) {
					String stopDebug2 = "";
				}
				
				String tieni="";
				
			}
			if(!grafo.vertexSet().contains(c.objId1)) {
				continue;
			}
			if(!grafo.vertexSet().contains(c.objId2)) {
				continue;
			}
			//if(grafo.getEdge(c.objId1, c.objId2)==null) {
				DefaultWeightedEdge e = grafo.addEdge(c.objId1, c.objId2);
				if(e!=null) {
					grafo.setEdgeWeight(e, c.peso);
				}
			//}
		}
		}
//		catch(Exception e) {
//			Graphs.addAllVertices(this.grafo, listaIds);
//			// Ora aggiungo gli edges
//			for(Collegamento c: collegamenti) {
//				
//				if(grafo.getEdge(c.objId1, c.objId2)==null) {
//					DefaultWeightedEdge e = grafo.addEdge(c.objId1, c.objId2);
//					grafo.setEdgeWeight(e, c.peso);
//				}
//			}
//		}
		
		
		
	
	
	public int trovaNumVicini(int identificativo) {
		for(int id: this.listaIds) {
			if(id==identificativo) {
				// Se lo trova ritorna i vicini
				return grafo.edgesOf(id).size();
			}
		}
		return -1;
	}
	public String ritornaVicini(int id) {
		int num = trovaNumVicini(id);
		if(num==-1) {
			return "Attenzione: l'identificativo non è valido";
		}
		if(num==0) {
			return "Non ci sono vertici oggetti associati";
		}
		return "I vertici associati sono: " +num;
	}
	
	public String risultatoRicorsione(int idNodoIniziale, int rimanenti) {
		Percorso p = this.avviaRicorsione(idNodoIniziale, rimanenti);
		String result="";
		for(ArtObject ao: p.oggettiScelti) {
			result+=ao.toString() + "\n";
		}
		result+=p.lungh;
		return result;
	}
	
	public Percorso avviaRicorsione(int idNodoIniziale, int rimanenti){
		Percorso inizializzata = new Percorso();
		Percorso risultato = ricorsione(idNodoIniziale, rimanenti, inizializzata, 0);
		return risultato;
		
		
	}
	
	
	public Percorso ricorsione(int idNodoIniziale, int rimanenti, Percorso percorsoFatto, double totLung){
		if(rimanenti==0) {
			return percorsoFatto;
		}
		List<Integer> tuttiVicini = Graphs.neighborListOf(this.grafo, idNodoIniziale);
		List<ArtObject> oggettiBuoni = new ArrayList<ArtObject>();
		ArtObject nodo = this.cercaOgg(idNodoIniziale);
		for(Integer v: tuttiVicini) {
			ArtObject ao = this.cercaOgg(v);
			if(!ao.getClassification().equals(nodo.getClassification()))
				continue;
			oggettiBuoni.add(ao);
		}
		// Prendo la copia del percorso

		List<Percorso> percorsiMigliori = new ArrayList<Percorso>();
		// Presi tutti gli oggetti buoni, li valuto uno ad uno
		for(ArtObject ao: oggettiBuoni) {
			Percorso percorsoCopy = new Percorso();
			percorsoCopy.lungh = percorsoFatto.lungh;
			for(ArtObject a: percorsoFatto.oggettiScelti) {
				percorsoCopy.oggettiScelti.add(ao);
				
			}
			
			percorsoCopy.oggettiScelti.add(ao);
			percorsoCopy.lungh += totLung;
			
			Percorso percorsoMigliorePerOb = this.ricorsione(idNodoIniziale, rimanenti-1, percorsoFatto, totLung);
			percorsiMigliori.add(percorsoMigliorePerOb);
			// E valuto i percorsiPossibili: scelgo il più grande
			
			
		}
		// Finito il ciclo ritorno il migliore tra questi
		
		double countWin=0;
		Percorso winner=null;
		for(Percorso poss: percorsiMigliori) {
			if(poss.lungh>countWin) {
				countWin = poss.lungh;
				winner = poss;
			}
		}
		
		return winner;
		
		
		
	}
	
}
