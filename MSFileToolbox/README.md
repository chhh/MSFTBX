# MSFileToolbox
This directory contains MSFTBX as both an Intellij IDEA project, to build/debug
as a regualr jar and an NBM (NetBeans Module). It is used in BatMass as an NBM.

Note, that both MSFileToolbox and MSFileToolboxLibs are wrapped into a NetBeans
module suite, you should add that as a dependency of you NetBeans application,
and mark both modules as required. As NetBeans dependencies are not transitive,
every time you use MSFileToolbox you'll have to add a dependency on MSFileToolboxLibs
as well. This is the desired behavior, which allows to avoid dependency clashes,
such as with Google Guava, whihc is a dependency here.
