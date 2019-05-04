package definitions.structures.abstr;

import java.util.List;

import definitions.structures.generic.Generator;

public interface IVec {

	int getDim();

	boolean elementOf(IVectorSpace space);

	boolean equals(IVec vec) throws Throwable;

	default List<IVec> getGenericBase() throws Throwable {
		return Generator.getFiniteDimensionalVectorSpace(getDim()).getGenericBase();
	}

}
