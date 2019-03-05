package cj.studio.nettest.jobserver.plugin.jobcore;

import cj.studio.ecm.net.CircuitException;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.jobserver.args.JobSender;

/**
 * 作业引擎<br>
 * 管理作业队列
 * 
 * @author caroceanjofers
 *
 */

public interface IJobEngine {
	void run();

	void stop();

	IJobQueue createJobQueue(JobSender sender, RequestFrame rf) throws CircuitException;

	void stopJobRunner(String mid, String sender);
}
