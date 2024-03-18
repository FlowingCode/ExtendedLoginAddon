/*-
 * #%L
 * Extended Login Add-on
 * %%
 * Copyright (C) 2023 Flowing Code
 * %%
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
 * #L%
 */
package com.flowingcode.vaadin.addons.extendedlogin;

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource("/src/test/java/com/flowingcode/vaadin/addons/extendedlogin/TestLoginLayoutView.java")
@DemoSource("/src/test/java/com/flowingcode/vaadin/addons/extendedlogin/TestLoginLayout.java")
@PageTitle("Login Layout Demo")
@SuppressWarnings("serial")
@Route(value = "extended-login/login-layout-demo", layout = ExtendedLoginDemoView.class)
public class LoginLayoutDemo extends Div {

  public LoginLayoutDemo() {
    setClassName("wrap-iframe");
    IFrame iframe = new IFrame("/extended-login/login-layout");
    iframe.setClassName("frame");
    iframe.setSizeFull();
    iframe.getElement().setAttribute("frameBorder", "0");
    add(iframe);
  }
}
