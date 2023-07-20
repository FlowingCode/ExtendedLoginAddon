/*-
 * #%L
 * Template Add-on
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

package com.flowingcode.vaadin.addons.extendedlogin.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.combobox.testbench.ComboBoxElement;
import com.vaadin.flow.component.html.testbench.AnchorElement;
import org.junit.Test;

public class ExtendedLoginOverlayIT extends AbstractViewTest {

  public ExtendedLoginOverlayIT() {
    super("login-overlay");
  }

  @Test
  public void testBasicBehaviour() {
    boolean comboBoxExists = $(ComboBoxElement.class).exists();
    assertTrue("ComboBox not present", comboBoxExists);
    boolean buttonExists = $(ButtonElement.class).exists();
    assertTrue("Button not present", buttonExists);
    if (buttonExists) {
      assertEquals("Sign In", $(ButtonElement.class).first().getText());
    }
    boolean anchorExists = $(AnchorElement.class).exists();
    assertTrue("Anchor not present", anchorExists);
    if (anchorExists) {
      assertEquals("Flowing Code Site", $(AnchorElement.class).first().getText());
    }
  }
}
