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
   * Generates a JavaScript snippet that finds the overlay wrapper element.
   * Handles both Vaadin 24 and Vaadin 25+ compatibility.
   *
   * @param action the JavaScript code to execute on the overlay wrapper
   * @return the complete JavaScript string with the overlay wrapper lookup and
   *         the provided action
   */
  static String getOverlayWrapperScript(String action) {
    return """
        setTimeout(() => {
          var overlayWrapper = document.getElementById('vaadinLoginOverlayWrapper');
          if (!overlayWrapper) {
            var loginOverlay = document.querySelector('vaadin-login-overlay');
            if (loginOverlay && loginOverlay.shadowRoot) {
              overlayWrapper = loginOverlay.shadowRoot
                .querySelector('vaadin-login-overlay-wrapper');
            }
          }
          if (!overlayWrapper) return;
          %s
        });
        """.formatted(action);
  }

  /**
   * Generates a JavaScript snippet that finds the form element.
   * Handles both Vaadin 24 and Vaadin 25+ compatibility.
   *
   * @param action the JavaScript code to execute on the form
   * @return the complete JavaScript string with the form lookup and the provided
   *         action
   */
  static String getFormWrapperScript(String action) {
    return """
        setTimeout(() => {
          var overlayFormWrapper = document.getElementById('vaadinLoginOverlayWrapper');
          if (!overlayFormWrapper) {
            overlayFormWrapper = document.querySelector('vaadin-login-overlay');
          }
          if (!overlayFormWrapper) return;
          var form = overlayFormWrapper.querySelector('form');
          if (form) {
            %s
          }
        });
        """.formatted(action);
  }

  /**
   * Generates a JavaScript snippet that finds the form wrapper containing form
   * elements.
   * Handles both Vaadin 24 and Vaadin 25+ compatibility.
   *
   * @param action the JavaScript code to execute on the form wrapper
   * @return the complete JavaScript string with the form wrapper lookup and the
   *         provided action
   */
  static String getLoginFormWrapperScript(String action) {
    return """
        setTimeout(() => {
          var overlayWrapper = document.getElementById('vaadinLoginOverlayWrapper');
          if (!overlayWrapper) {
            var loginOverlay = document.querySelector('vaadin-login-overlay');
            if (loginOverlay && loginOverlay.shadowRoot) {
              overlayWrapper = loginOverlay.shadowRoot
                .querySelector('vaadin-login-overlay-wrapper');
            }
          }
          if (!overlayWrapper) return;
          var formWrapper = overlayWrapper.querySelector('vaadin-login-form-wrapper');
          if (!formWrapper) return;
          %s
        });
        """.formatted(action);
  }

  /**
   * Replaces the contents of the login form with the provided elements.
   * Clears existing form contents and appends the provided elements.
   *
   * @param withElement the elements to add to the form
   */
  default void replaceFormComponents(HasElement... withElement) {
    this.getElement().executeJs(getFormWrapperScript("form.replaceChildren();"));

    for (HasElement we : withElement) {
      getElement().appendChild(we.getElement());
      this.getElement().executeJs(getFormWrapperScript("form.appendChild($0);"), we.getElement());
    }
  }

  /**
   * Replaces the header/brand component of the login overlay.
   * Clears the brand section and appends the provided element.
   *
   * @param withElement the element to set as the new brand/header
   */
  default void replaceHeaderComponent(HasElement withElement) {
    getElement().appendChild(withElement.getElement());

    this.getElement().executeJs(
        getOverlayWrapperScript(
            """
                var brand = overlayWrapper.shadowRoot ? overlayWrapper.shadowRoot.querySelector('[part="brand"]') : overlayWrapper.querySelector('[part="brand"]');
                if (brand) {
                  brand.replaceChildren();
                }
                """));

    this.getElement().executeJs(
        getOverlayWrapperScript(
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
    this.getElement().executeJs(
        getLoginFormWrapperScript(
            """
                var forgotPassword = formWrapper.querySelector('[slot="forgot-password"]');
                if (forgotPassword) {
                  forgotPassword.remove();
                }
                """));
  }

  /**
   * Replaces the forgot password component in the login overlay.
   * Clears the forgot password section and appends the provided element.
   *
   * @param withElement the element to set as the new forgot password component
   * 
   */
  default void replaceForgotPassword(HasElement withElement) {
    withElement.getElement().setAttribute("slot", "forgot-password");
    getElement().appendChild(withElement.getElement());
    this.removeForgotPassword();
    this.getElement().executeJs(
        getLoginFormWrapperScript(
            """
                formWrapper.appendChild($0);
                """),
        withElement);
  }

  /**
   * Removes the default submit button.
   */
  default void removeSubmitButton() {
    this.getElement().executeJs(
        getLoginFormWrapperScript(
            """
                var submitButton = formWrapper.querySelector('[slot="submit"]');
                if (submitButton) {
                  submitButton.remove();
                }
                """));
  }

}
