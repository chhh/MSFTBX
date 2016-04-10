# MSFileToolbox
This directory contains `MSFTBX` as both an Intellij IDEA project and a NetBeans
Module (NBM) project. You can try opening it in both IDEs.
To build/debug as a regualr _jar_ use IDEA, to build/debug as a part of BatMass,
open the module in NetBeans.

Note, that both `MSFileToolbox` and `MSFileToolboxLibs` are wrapped into a
NetBeans module suite `MSFTBX` - the top level directory this file sits in.
You should add that as a dependency of you NetBeans application if you want to
use it in a NetBeans Platform project (mark both modules as required).
As NetBeans dependencies are not transitive, every time you use `MSFileToolbox`
you'll have to add a dependency on `MSFileToolboxLibs` as well. This is the
desired behavior, which allows to avoid dependency clashes, such as with Google
Guava, which is a dependency here.
