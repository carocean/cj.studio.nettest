package cj.studio.nettest.be.service;

import cj.studio.nettest.be.args.RequestFrame;

public interface IRequestFrameService {
	RequestFrame getMyRequestFrame(String mid,String creator);
}
