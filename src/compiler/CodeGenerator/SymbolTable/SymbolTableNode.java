package compiler.CodeGenerator.SymbolTable;

import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.Exceptions.NameAlreadyExistsException;
import compiler.CodeGenerator.Exceptions.NameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableNode {

	private SymbolTableNode prev;
	private SymbolTableNode next;
	private Map<String, Descriptor> table;

	public SymbolTableNode() {
		prev = new SymbolTableNode();
		next = new SymbolTableNode();
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

}
