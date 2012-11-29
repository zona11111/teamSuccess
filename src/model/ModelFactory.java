package model;

import process.Dispatcher;
import qusystem.IModelFactory;

public class ModelFactory implements IModelFactory{
	
	private static IModelFactory instance = new ModelFactory();
	
	private ModelFactory(){}

	public static IModelFactory getInstance() {
		return instance;
	}

	@Override
	public Object createModel(Dispatcher arg0) {
		return new Model(arg0);
	}

}
