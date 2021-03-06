/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.ml.integration.ui.pages.mlui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.wso2.carbon.ml.integration.ui.pages.exceptions.InvalidPageException;
import org.wso2.carbon.ml.integration.ui.pages.exceptions.MLUIPageCreationException;

import java.io.File;

public class DatasetVersionPage extends MLUIPage {

    private static final Log logger = LogFactory.getLog(DatasetVersionPage.class);

    /**
     * Creates a DatasetVersionPage
     *
     * @param driver    Instance of the web driver
     * @throws org.wso2.carbon.ml.integration.ui.pages.exceptions.MLUIPageCreationException
     */
    public DatasetVersionPage(WebDriver driver) throws MLUIPageCreationException {
        super(driver);
    }

    /**
     * Upload dataset version
     * @param dataFile
     * @param sourceType
     * @param dataFormat
     * @param columnHeader
     * @return
     * @throws org.wso2.carbon.ml.integration.ui.pages.exceptions.InvalidPageException
     */
    public MLDatasetsPage uploadDatasetVersion(File dataFile, String sourceType, String dataFormat, String columnHeader) throws InvalidPageException {
        try {
            WebElement sourceTypeElement = driver.findElement(By.xpath(mlUIElementMapper.getElement("dataset.source.type")));
            WebElement pathElement = driver.findElement(By.xpath(mlUIElementMapper.getElement("dataset.path")));
            WebElement dataFormatElement = driver.findElement(By.xpath(mlUIElementMapper.getElement("dataset.data.format")));
            WebElement columnHeaderElement = driver.findElement(By.xpath(mlUIElementMapper.getElement("dataset.column.header")));

            sourceTypeElement.sendKeys(sourceType);
            pathElement.sendKeys(dataFile.getPath());
            dataFormatElement.sendKeys(dataFormat);
            columnHeaderElement.sendKeys(columnHeader);

            driver.findElement(By.xpath(mlUIElementMapper.getElement("create.dataset.version.button"))).click();
            return new MLDatasetsPage(driver);
        } catch (MLUIPageCreationException e) {
            throw new InvalidPageException("Error occurred while uploading dataset version: ", e);
        }
    }
}
