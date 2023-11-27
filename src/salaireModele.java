

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;



public class salaireModele extends AbstractTableModel{
	
	private List<employees>liste=new ArrayList<>();
	private String titres[]= {"id","nom complet","salaire","Date"};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return liste.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titres.length;
	}

	@Override
	public Object getValueAt(int l, int c) {
		switch(c)
		{
		case 0:return liste.get(l).getId();
		case 1:return liste.get(l).getName();
		case 2:return liste.get(l).getSalaire();
		case 3:return liste.get(l).getDob();
        
        
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		
		return titres[column];
	}
	public void charger(List<employees>l)
	{
		liste=l;
		fireTableDataChanged();
	}

}



