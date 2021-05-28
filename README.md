# Overview

>
>![overview](overview.PNG)
>

The sdnbox is a heterogeneous openflow network management platform, developed by vnetlab team in dlut. You can manipulate any in-ready openflow network on the platform, just make sure the network's control plane API be compatible with sdnbox. The sdnbox so far works fine with common openflow API, su floodlight, ryu as underlying network operational system.

# Features

1. manipulate already existing underlying openflow network.
2. create custom network experiment script.

# Content Catalog

1. sdnbox_deployable: providing compiled deployable version.
2. sdnbox_resource: providing platform required resource, such as dbscript and ofcontroller.
3. sdnbox_source: providing version of source code, can be re compiled.
