package cj.studio.nettest.jobserver.service;

import cj.studio.nettest.jobserver.args.JobSender;
public interface IJobServerService {
	void addJob(String mid,JobSender sender);
}
