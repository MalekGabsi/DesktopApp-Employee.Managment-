

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;



public class table extends AbstractTableModel{
	
	private List<employees>liste=new ArrayList<>();
	private String titres[]= {"id","nom","prenom","date de naissance","sexe","numéro","adresse"};

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
		case 2:return liste.get(l).getSurname();
		case 3:return liste.get(l).getDob();
		case 4:return liste.get(l).getSex();
        case 5:return liste.get(l).getPhone();
        case 6:return liste.get(l).getAddress();
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

