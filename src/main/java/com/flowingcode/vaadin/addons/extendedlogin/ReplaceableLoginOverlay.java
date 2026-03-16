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
package com.flowingcode.vaadin.addons.extendedlogin;

import com.vaadin.flow.component.HasElement;

/**
 * Mixin interface providing methods for replacing contents of the LoginOverlay.
 *
 * @author mlopez
 */
public interface ReplaceableLoginOverlay extends HasElement {

  /**
   * Replaces the contents of the login form with the provided elements. Clears
   * existing form
   * contents and appends the provided elements.
   *
   * @param withElement the elements to add to the form
   */
  default void replaceFormComponents(HasElement... withElement) {
    this.getElement().executeJs(LoginOverlayUtils.getFormWrapperScript("form.replaceChildren();"));

    for (HasElement we : withElement) {
      getElement().appendChild(we.getElement());
      this.getElement()
          .executeJs(LoginOverlayUtils.getFormWrapperScript("form.appendChild($0);"), we.getElement());
    }
  }

  /**
   * Replaces the header/brand component of the login overlay. Clears the brand
   * section and appends
   * the provided element.
   *
   * @param withElement the element to set as the new brand/header
   */
  default void replaceHeaderComponent(HasElement withElement) {
    getElement().appendChild(withElement.getElement());

    this.getElement()
        .executeJs(
            LoginOverlayUtils.getOverlayWrapperScript(
                """
                    var brand = overlayWrapper.shadowRoot ? overlayWrapper.shadowRoot.querySelector('[part="brand"]') : overlayWrapper.querySelector('[part="brand"]');
                    if (brand) {
                      brand.replaceChildren();
                    }
                    """));

    this.getElement()
        .executeJs(
            LoginOverlayUtils.getOverlayWrapperScript(
                """
                    var brand = overlayWrapper.shadowRoot ? overlayWrapper.shadowRoot.querySelector('[part="brand"]') : overlayWrapper.querySelector('[part="brand"]');
                    if (brand) {
                      brand.appendChild($0);
                    }
                    """),
            withElement);
  }

  /**
   * Removes the forgot password link from the login form.
   */
  default void removeForgotPassword() {
    this.getElement()
        .executeJs(
            LoginOverlayUtils.getLoginFormWrapperScript(
                """
                    var forgotPassword = formWrapper.querySelector('[slot="forgot-password"]');
                    if (forgotPassword) {
                      forgotPassword.remove();
                    }
                    """));
  }

  /**
   * Replaces the forgot password component in the login overlay. Clears the
   * forgot password section
   * and appends the provided element.
   *
   * @param withElement the element to set as the new forgot password component
   *
   */
  default void replaceForgotPassword(HasElement withElement) {
    withElement.getElement().setAttribute("slot", "forgot-password");
    getElement().appendChild(withElement.getElement());
    this.removeForgotPassword();
    this.getElement()
        .executeJs(
            LoginOverlayUtils.getLoginFormWrapperScript(
                """
                    formWrapper.appendChild($0);
                    """),
            withElement);
  }

  /**
   * Removes the default submit button.
   */
  default void removeSubmitButton() {
    this.getElement()
        .executeJs(
            LoginOverlayUtils.getLoginFormWrapperScript(
                """
                    var submitButton = formWrapper.querySelector('[slot="submit"]');
                    if (submitButton) {
                      submitButton.remove();
                    }
                    """));
  }

}
