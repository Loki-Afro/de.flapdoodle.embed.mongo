/**
 * Copyright (C) 2011
 *   Michael Mosmann <michael@mosmann.de>
 *   Martin Jöhren <m.joehren@googlemail.com>
 *
 * with contributions from
 * 	konstantin-ba@github,Archimedes Trajano (trajano@github)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.flapdoodle.embedmongo.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.flapdoodle.process.config.io.ProcessOutput;
import de.flapdoodle.process.config.store.ArtifactStoreInUserHome;
import de.flapdoodle.process.config.store.IArtifactStoragePathNaming;
import de.flapdoodle.process.extract.ITempNaming;
import de.flapdoodle.process.extract.UUIDTempNaming;
import de.flapdoodle.process.io.LoggingOutputStreamProcessor;
import de.flapdoodle.process.io.progress.IProgressListener;
import de.flapdoodle.process.io.progress.LoggingProgressListener;
import de.flapdoodle.process.io.progress.StandardConsoleProgressListener;
import de.flapdoodle.process.runtime.ICommandLinePostProcessor;

/**
 *
 */
public class RuntimeConfig {

	private IProgressListener progressListener = new StandardConsoleProgressListener();
	private String downloadPath = "http://fastdl.mongodb.org/";
	private IArtifactStoragePathNaming artifactStorePath = new ArtifactStoreInUserHome();
	private ITempNaming defaultfileNaming = new UUIDTempNaming();
	private ITempNaming executableNaming = defaultfileNaming;
	private ProcessOutput mongodOutputConfig = MongodProcessOutputConfig.getDefaultInstance();
	private ICommandLinePostProcessor commandLinePostProcessor = new ICommandLinePostProcessor.Noop();

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setProgressListener(IProgressListener progressListener) {
		this.progressListener = progressListener;
	}

	public ITempNaming getDefaultfileNaming() {
		return defaultfileNaming;
	}

	public void setDefaultfileNaming(ITempNaming defaultfileNaming) {
		this.defaultfileNaming = defaultfileNaming;
	}

	public ITempNaming getExecutableNaming() {
		return executableNaming;
	}

	public void setExecutableNaming(ITempNaming executableNaming) {
		this.executableNaming = executableNaming;
	}

	public IProgressListener getProgressListener() {
		return progressListener;
	}

	public IArtifactStoragePathNaming getArtifactStorePathNaming() {
		return artifactStorePath;
	}

	public void setArtifactStorePathNaming(IArtifactStoragePathNaming value) {
		this.artifactStorePath = value;
	}

	public ProcessOutput getMongodOutputConfig() {
		return mongodOutputConfig;
	}

	public void setMongodOutputConfig(ProcessOutput mongodOutputConfig) {
		this.mongodOutputConfig = mongodOutputConfig;
	}

	public void setCommandLinePostProcessor(ICommandLinePostProcessor commandLinePostProcessor) {
		this.commandLinePostProcessor = commandLinePostProcessor;
	}

	public ICommandLinePostProcessor getCommandLinePostProcessor() {
		return commandLinePostProcessor;
	}

	public static RuntimeConfig getInstance(Logger logger) {
		RuntimeConfig ret = new RuntimeConfig();
		ret.setMongodOutputConfig(MongodProcessOutputConfig.getInstance(logger));
		ret.setProgressListener(new LoggingProgressListener(logger, Level.FINE));
		return ret;
	}
}
