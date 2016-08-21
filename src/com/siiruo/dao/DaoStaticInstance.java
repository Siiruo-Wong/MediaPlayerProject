package com.siiruo.dao;

public class DaoStaticInstance {
	private static VideoDao videoDao;
	private static HistoryDao historyDao;
	private static CollectionDao collectionDao;
	private static HistoryTreeDao historyTreeDao;
	private static CollectionTreeDao collectionTreeDao;
	public synchronized static VideoDao getVideoDao(){
		if(videoDao==null) {
			videoDao=new VideoDaoImpl();
		}
		return videoDao;
	}
public synchronized static HistoryDao getHistoryDao(){
		if(historyDao==null){
			historyDao=new HistoryDaoImpl();
		}
		return historyDao;
	}
public synchronized static CollectionDao getCollectionDao(){
	if(collectionDao==null){
		collectionDao=new CollectionDaoImpl();
	}
	return collectionDao;
}
public synchronized static HistoryTreeDao getHistoryTreeDao(){
	if(historyTreeDao==null){
		historyTreeDao=new HistoryTreeDaoImpl();
	}
	return historyTreeDao;
}
public synchronized static CollectionTreeDao getCollectionTreeDao(){
	if(collectionTreeDao==null){
		collectionTreeDao=new CollectionTreeDaoImpl();
	}
	return collectionTreeDao;
}

}
