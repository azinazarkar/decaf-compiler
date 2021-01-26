package compiler.CodeGenerator.SymbolTable.Utility;

public class ArrayDescriptor extends Descriptor {

	private Type subType;
	private int dimensionCount;

	public ArrayDescriptor( String name, ArrayType arrayType ) {
		super( name, Type.ARRAY );
		this.subType = arrayType.getSubType();
		this.dimensionCount = arrayType.getDimensionCount();
	}

	public Type getSubType() {
		return subType;
	}

	public void setSubType(Type subType) {
		this.subType = subType;
	}

	public int getDimensionCount() {
		return dimensionCount;
	}

	public void setDimensionCount(int dimensionCount) {
		this.dimensionCount = dimensionCount;
	}
}
