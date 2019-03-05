package cj.studio.nettest.jobserver.service;

import cj.studio.ecm.net.CircuitException;
import cj.studio.nettest.jobserver.args.JobSender;

public interface IJobServerService {
	void addJob(String mid, JobSender sender) throws CircuitException;

	void addJobByRunner(String mid, String runnerid, JobSender sender) throws CircuitException;

	void stopJobRunner(String mid, String sender) throws CircuitException;
}
