package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

public class MethodInvokingActionCommandExecutor extends ArgumentConvertingMethodInvoker implements ParameterizableActionCommandExecutor {
    public void execute() {
        execute(Collections.EMPTY_MAP);
    }

    public void execute(Map params) {
        setArguments(params.values().toArray());
        try {
            prepare();
            invoke();
        } catch (Exception e) {
            throw new RuntimeException("Error executing method " + getTargetMethod() + " with arguments " + getArguments());
        }
    }
}