package org.uncodeframework.core.common.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 任务执行类
 *
 */
public class QuartzJob implements Job {

  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "★★★★★★★★★★★");  
  }
}