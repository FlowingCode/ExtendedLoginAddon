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
import com.flowingcode.vaadin.addons.demo.TabbedDemo;
import com.flowingcode.vaadin.addons.demo.ThemeChangeObserver;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource(
    "/src/test/java/com/flowingcode/vaadin/addons/extendedlogin/TestExtendedLoginOverlayView.java")
@PageTitle("Extended Login Overlay Demo")
@SuppressWarnings("serial")
@Route(value = "extended-login/login-overlay-demo", layout = ExtendedLoginDemoView.class)
public class ExtendedLoginOverlayDemo extends Div implements ThemeChangeObserver {

  private final IFrame iframe;

  public ExtendedLoginOverlayDemo() {
    setClassName("wrap-iframe");
    iframe = new IFrame("/extended-login/login-overlay");
    iframe.setSizeFull();
    iframe.getElement().setAttribute("frameBorder", "0");
    add(iframe);
  }

  @Override
  public void onThemeChange(String themeName) {
    TabbedDemo.applyTheme(iframe.getElement(), themeName);
  }

}
