package view.gui.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListaSuport
{
	private final SimpleStringProperty item0;
	private final SimpleStringProperty item1;
	private final SimpleStringProperty item2;
	private final SimpleStringProperty item3;
	private final SimpleBooleanProperty item4;
	
	public ListaSuport(String item0, String item1, String item2, String item3)
	{
		this.item0 = new SimpleStringProperty (item0);
		this.item1 = new SimpleStringProperty (item1);
		this.item2 = new SimpleStringProperty (item2);
		this.item3 = new SimpleStringProperty (item3);
		this.item4 = new SimpleBooleanProperty(false);
	}
	
	public ListaSuport(String item0, String item1, String item2, String item3, boolean item4)
	{
		this.item0 = new SimpleStringProperty (item0);
		this.item1 = new SimpleStringProperty (item1);
		this.item2 = new SimpleStringProperty (item2);
		this.item3 = new SimpleStringProperty (item3);
		this.item4 = new SimpleBooleanProperty(item4);
	}

	public String getItem0()
	{
		return item0.get();
	}
	
	public void setItem0 (String fName)
	{
		item0.set (fName);
	}
		
	public String getItem1()
	{
		return item1.get();
	}
	
	public void setItem1 (String fName)
	{
		item1.set (fName);
	}
	
	public String getItem2()
	{
		return item2.get();
	}
	
	public void setItem2 (String fName)
	{
		item2.set (fName);
	}
	
	public String getItem3()
	{
		return item3.get();
	}
	
	public void setItem3 (String fName)
	{
		item3.set (fName);
	}
	
	public boolean isSelected() 
	{
		return item4.get();
	}

	public SimpleBooleanProperty selectedProperty() 
	{
		return item4;
	}

	public void setItem4 (Boolean fName)
	{
		item4.set (fName);
	}
}