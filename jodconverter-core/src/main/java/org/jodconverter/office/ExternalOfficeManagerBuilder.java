/*
 * Copyright 2004 - 2012 Mirko Nasato and contributors
 *           2016 - 2017 Simon Braconnier and contributors
 *
 * This file is part of JODConverter - Java OpenDocument Converter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jodconverter.office;

import com.sun.star.lib.uno.helper.UnoUrl;

/** Helper class used to creates ExternalOfficeManager instances. */
public class ExternalOfficeManagerBuilder {

  private OfficeConnectionProtocol connectionProtocol;
  private int portNumber;
  private String pipeName = "office";
  private boolean connectOnStart = true;

  /** Creates a new instance of the class. */
  public ExternalOfficeManagerBuilder() {

    connectionProtocol = OfficeConnectionProtocol.SOCKET;
    portNumber = 2002;
    pipeName = "office";
    connectOnStart = true;
  }

  /**
   * Builds a new {@link ExternalOfficeManager}.
   *
   * @return the created {@link ExternalOfficeManager}.
   */
  public OfficeManager build() {

    final UnoUrl unoUrl =
        connectionProtocol == OfficeConnectionProtocol.SOCKET
            ? UnoUrlUtils.socket(portNumber)
            : UnoUrlUtils.pipe(pipeName);
    return new ExternalOfficeManager(unoUrl, connectOnStart);
  }

  /**
   * Sets the connection protocol.
   *
   * @param connectionProtocol the new protocol to set.
   * @return the updated configuration.
   */
  public ExternalOfficeManagerBuilder setConnectionProtocol(
      final OfficeConnectionProtocol connectionProtocol) {

    this.connectionProtocol = connectionProtocol;
    return this;
  }

  /**
   * Sets whether a connection is attempted on start.
   *
   * @param connectOnStart {@code true} if a connection should be attempted on start, {@code false}
   *     otherwise.
   * @return the updated configuration.
   */
  public ExternalOfficeManagerBuilder setConnectOnStart(final boolean connectOnStart) {

    this.connectOnStart = connectOnStart;
    return this;
  }

  /**
   * Sets the pipe name that will be use to communicate with office.
   *
   * @param pipeName the pipe name to use.
   * @return the updated configuration.
   */
  public ExternalOfficeManagerBuilder setPipeName(final String pipeName) {

    this.pipeName = pipeName;
    return this;
  }

  /**
   * Sets the port number that will be use to communicate with office.
   *
   * @param portNumber the port number to use.
   * @return the updated configuration.
   */
  public ExternalOfficeManagerBuilder setPortNumber(final int portNumber) {

    this.portNumber = portNumber;
    return this;
  }
}