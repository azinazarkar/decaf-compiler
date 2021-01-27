package compiler.CodeGenerator.SymbolTable;

import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.Exceptions.NameAlreadyExistsException;
import compiler.CodeGenerator.Exceptions.NameNotFoundException;

import java.util.*;

public class SymbolTableNode {

	SymbolTableNode prev;
	List<SymbolTableNode> next;
	private Map<String, Descriptor> table;
	private int level;
	private String scopeName;
	protected int nextIndex;

	public SymbolTableNode( String scopeName ) {
		this( null, scopeName, 0 );
	}

	public SymbolTableNode( SymbolTableNode symbolTableNode, String scopeName, int level ) {
		next = new ArrayList<>( 0 );
		prev = symbolTableNode;
		table = new HashMap<>();
		this.level = level;
		this.scopeName = scopeName;
		this.nextIndex = -1;
	}

	public void addEntry( String id, Descriptor descriptor ) {
		if ( table.containsKey( id ) )
			throw new NameAlreadyExistsException( id );
		table.put( id, descriptor );
	}

	public int getEntryCount() {
		return table.size();
	}

	public Set<Map.Entry<String, Descriptor>> getAllEntries() {
		return this.table.entrySet();
	}

	protected void addNext( String name ) {
		next.add( new SymbolTableNode( this, name, level + 1 ) );
		nextIndex++;
	}

	protected SymbolTableNode getNext() {
		return next.get( nextIndex );
	}

	protected void resetIndex() {
		nextIndex = 0;
	}

	public Descriptor getDescriptor( String id ) {
		if ( table.containsKey( id ) )
			return table.get( id );
		else if ( prev == null )
			throw new NameNotFoundException( id );
		else
			return prev.getDescriptor( id );
	}

	public SymbolTableNode getPrev() {
		return prev;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getScopeName() {
		return scopeName;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}

	@Override
	public String toString() {
		String returnValue = scopeName + ", " + level + ", ";
		for ( Map.Entry<String, Descriptor> e : table.entrySet() )
			returnValue = returnValue + " (" + e.getKey() + "," + e.getValue() + ")";
		return returnValue;
	}
}
