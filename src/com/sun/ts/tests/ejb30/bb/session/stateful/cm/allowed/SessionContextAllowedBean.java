/*
 * Copyright (c) 2007, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id$
 */

package com.sun.ts.tests.ejb30.bb.session.stateful.cm.allowed;

import com.sun.ts.tests.ejb30.common.allowed.SessionContextAllowedIF;
import com.sun.ts.tests.ejb30.common.allowed.SessionContextAllowedBeanBase;
import com.sun.ts.tests.ejb30.common.allowed.SessionContextAllowedLocalIF;
import com.sun.ts.tests.ejb30.common.allowed.stateful.StatefulOperations;
import com.sun.ts.tests.ejb30.common.allowed.stateful.TimerLocalIF;
import java.util.Properties;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.interceptor.Interceptors;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateful;

@Stateful(name = "SessionContextAllowedBean")
@Remote({ SessionContextAllowedIF.class })
@Local({ SessionContextAllowedLocalIF.class })
@Interceptors({
    com.sun.ts.tests.ejb30.common.allowed.stateful.StatefulCancelInterceptor.class })

@EJB(name = "ejb/TimerEJB", beanName = "TimerEJB", beanInterface = TimerLocalIF.class)
public class SessionContextAllowedBean extends SessionContextAllowedBeanBase
    implements SessionContextAllowedIF, SessionContextAllowedLocalIF,
    java.io.Serializable {

  @Resource(name = "ejbContext")
  public void setSessionContext(SessionContext sc) {
    super.setSessionContext(sc);
  }

  @Remove
  public void remove() {
    super.remove();
  }

  @Override
  public Properties runOperations(SessionContext sctx) {
    return StatefulOperations.getInstance().run(sctx);
  }

}
