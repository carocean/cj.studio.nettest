package cj.studio.nettest.be.plugin.service;

import org.bson.Document;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.nettest.be.service.IRetrieveConfigService;

@CjService(name = "retrieveConfigService")
public class RetrieveConfigService implements IRetrieveConfigService {
	@CjServiceRef(refByName = "mongodb.netos.test")
	ICube test;

	@Override
	public void retrieveAll(String existsUser, String otherUser) {
		retrieveHeaders(existsUser,otherUser);
		retrieveHeadlines(existsUser,otherUser);
		retrieveHosts(existsUser,otherUser);
		retrieveNetprotocols(existsUser,otherUser);
		retrieveParameters(existsUser,otherUser);
		retrieveReports(existsUser,otherUser);
		retrieveStrategies(existsUser,otherUser);
		retrieveContentAnys(existsUser,otherUser);
		retrieveContentFormdatas(existsUser,otherUser);
		retrieveContentTypes(existsUser,otherUser);
		retrieveContentXwwws(existsUser,otherUser);
	}

	private void retrieveHeaders(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.headers", filter, update);
	}

	private void retrieveHeadlines(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.headlines", filter, update);
	}

	private void retrieveHosts(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.hosts", filter, update);
	}

	private void retrieveNetprotocols(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.netprotocols", filter, update);
	}

	private void retrieveParameters(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.parameters", filter, update);
	}

	private void retrieveReports(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.reports", filter, update);
	}

	private void retrieveStrategies(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("runner.strategies", filter, update);
	}

	private void retrieveContentAnys(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.content.anys", filter, update);
	}

	private void retrieveContentFormdatas(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.content.formdatas", filter, update);
	}

	private void retrieveContentTypes(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.content.types", filter, update);
	}

	private void retrieveContentXwwws(String existsUser, String otherUser) {
		Document filter=Document.parse(String.format("{'tuple.creator':'%s'}", existsUser));
		Document update=Document.parse(String.format("{$set:{'tuple.creator':'%s'}}", otherUser));
		test.updateDocs("request.content.xwwws", filter, update);
	}

}
