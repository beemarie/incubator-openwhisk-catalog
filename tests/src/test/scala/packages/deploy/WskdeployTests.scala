/*
 * Copyright 2015-2016 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package packages.combinators

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import common.{ TestHelpers, Wsk, WskProps, WskTestHelpers }

@RunWith(classOf[JUnitRunner])
class WskdeployTests extends TestHelpers with WskTestHelpers {

  implicit val wskprops = WskProps()
  val wsk = new Wsk()
  val wskdeployAction = "/whisk.system/deploy/wskdeploy"

  behavior of "deploy wskdeploy"

  it should "indicates this action is successfully called" in {
      val run = wsk.action.invoke(wskdeployAction, Map())
      withActivation(wsk.activation, run) {
          activation =>
              activation.response.success shouldBe true
      }
  }
}
