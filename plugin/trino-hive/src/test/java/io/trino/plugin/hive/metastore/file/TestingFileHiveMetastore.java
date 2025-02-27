/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.hive.metastore.file;

import io.trino.filesystem.Location;
import io.trino.filesystem.TrinoFileSystemFactory;
import io.trino.plugin.hive.NodeVersion;
import io.trino.plugin.hive.metastore.HiveMetastoreConfig;

import java.io.File;

import static io.trino.plugin.hive.HiveTestUtils.HDFS_FILE_SYSTEM_FACTORY;

public final class TestingFileHiveMetastore
{
    private TestingFileHiveMetastore() {}

    public static FileHiveMetastore createTestingFileHiveMetastore(File catalogDirectory)
    {
        return createTestingFileHiveMetastore(HDFS_FILE_SYSTEM_FACTORY, Location.of(catalogDirectory.toURI().toString()));
    }

    public static FileHiveMetastore createTestingFileHiveMetastore(TrinoFileSystemFactory fileSystemFactory, Location catalogDirectory)
    {
        return new FileHiveMetastore(
                new NodeVersion("testversion"),
                fileSystemFactory,
                new HiveMetastoreConfig().isHideDeltaLakeTables(),
                new FileHiveMetastoreConfig()
                        .setCatalogDirectory(catalogDirectory.toString())
                        .setMetastoreUser("test"));
    }
}
