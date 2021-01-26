package compiler.CodeGenerator.SymbolTable.Utility;

public class ArrayType {

	private Type subType;
	private int dimensionCount;

	public ArrayType(Type subType, int dimensionCount) {
		this.subType = subType;
		this.dimensionCount = dimensionCount;
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
