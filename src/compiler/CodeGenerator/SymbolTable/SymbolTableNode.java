package compiler.CodeGenerator.SymbolTable;

import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.Exceptions.NameAlreadyExistsException;
import compiler.CodeGenerator.Exceptions.NameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableNode {

	SymbolTableNode prev;
	SymbolTableNode next;
	private Map<String, Descriptor> table;

	public SymbolTableNode() {
		table = new HashMap<>();
	}

	public SymbolTableNode( SymbolTableNode symbolTableNode ) {
		prev = symbolTableNode;
		table = new HashMap<>();
	}

	public void addEntry( String name, Descriptor descriptor ) {
		if ( table.containsKey( name ) )
			throw new NameAlreadyExistsException( name );
		table.put( name, descriptor );
	}

	public Descriptor getDescriptor( String name ) {
		if ( table.containsKey( name ) )
			return table.get( name );
		else
			throw new NameNotFoundException( name );
	}

	@Override
	public String toString() {
		String returnValue = "";
		for ( Map.Entry<String, Descriptor> e : table.entrySet() )
			returnValue = returnValue + " (" + e.getKey() + "," + e.getValue() + ")";
		return returnValue;
	}
}
