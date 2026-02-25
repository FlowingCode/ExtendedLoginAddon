/*-
 * #%L
 * Extended Login Add-on
 * %%
 * Copyright (C) 2023 - 2026 Flowing Code
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

package com.flowingcode.vaadin.addons.extendedlogin.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.vaadin.flow.component.formlayout.testbench.FormLayoutElement;
import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.flow.component.html.testbench.H2Element;
import com.vaadin.flow.component.html.testbench.ImageElement;
import org.junit.Test;

public class LoginLayoutIT extends AbstractViewTest {

  public LoginLayoutIT() {
    super("extended-login/login-layout");
  }

  @Test
  public void testBasicBehaviour() {
    VaadinLoginOverlayWrapperElement vlow = $(VaadinLoginOverlayWrapperElement.class).first();
    VaadinLoginFormWrapperElement vlfw = $(VaadinLoginFormWrapperElement.class).first();
    assertTrue(
        "Custom image is not present",
        vlow.$(ImageElement.class).attribute("alt", "Login image").exists());
    boolean h2exists = vlfw.$(H2Element.class).exists();
    assertTrue("H2 is not present", h2exists);
    if (h2exists) {
      assertEquals("Change Password", vlfw.$(H2Element.class).first().getText());
    }
    boolean divSlotFormExists = $(DivElement.class).attribute("slot", "form").exists();
    assertTrue("Div with slot form is not present", divSlotFormExists);
    if (divSlotFormExists) {
      boolean divContainsFormLayoutExists =
          $(DivElement.class).attribute("slot", "form").first().$(FormLayoutElement.class).exists();
      assertTrue("Div does not contain form layout", divContainsFormLayoutExists);
    }
  }
}
