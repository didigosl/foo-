package com.technology.greenenjoyshoppingstreet.utils.net;


import com.technology.greenenjoyshoppingstreet.utils.net.core.NetEngine;

/**
 * 网络请求取消者
 *
 * @version V1.0
 *  2016.12.03
 */
public class Canceller {
    /**
     * 网络请求引擎
     */
    private NetEngine engine;
    /** Task tag. */
    private Object taskTag = String.valueOf(System.nanoTime());


    /**
     * Instantiates a new Canceller.
     *
     * @param engine the engine
     */
    public Canceller(NetEngine engine) {
        this.engine = engine;
        if (engine != null) {
            engine.addTaskTag(taskTag);
        }

    }

    /**
     * 取消网络请求
     */
    public void cancel() {
        if (engine != null) {
            engine.cancelTask(this);
        }

    }

    /**
     * 获取任务ID
     *
     * @return the task tag
     */
    public Object getTaskTag() {
        return taskTag;
    }


}
