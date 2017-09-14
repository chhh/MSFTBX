## The process of setting up Thrift
I already had MSVS 2015 (cmake -G "Visual Studio 14").

Install [cmake](https://cmake.org/download/), [flex, bison](https://github.com/lexxmark/winflexbison).  
Make sure they're on path:
```
PS C:\Programs\thrift\thrift-0.10.0\compiler\cpp> which cmake
/c/Programs/CMake/cmake-3.9.2/bin/cmake
PS C:\Programs\thrift\thrift-0.10.0\compiler\cpp> which flex
/c/Programs/FlexBison/win_flex_bison-2.5.10/flex
PS C:\Programs\thrift\thrift-0.10.0\compiler\cpp> which bison
/c/Programs/FlexBison/win_flex_bison-2.5.10/bison
```

Get a copy of the Thrift repo ([direct zip link](https://github.com/apache/thrift/archive/master.zip)) from https://github.com/apache/thrift  
I got this particular commit: https://github.com/apache/thrift/commit/a62efa4109c34c88d48f529a044e89ce854daa16


## Following new instructions for Win builds
From: https://github.com/apache/thrift/tree/0.11.0/build/wincpp    
Copied file over here: _README-thrift-win-build-instructions.md_

- Downloaded tarball of thrift sources (v0.10): https://github.com/apache/thrift/tree/0.11.0.
  - Note that v0.11 was available, but only 0.10 produced a build result
  - Also note that v0.10 source tree does not contain the instructions that I was using to build everything.
- Created `workspace` directory and unzipped _0.11_ branch tarball to it.


- Downloaded zlib 1.2.11: http://zlib.net/
- Updated zlib version in `workspace\scripts\tpversions.bat`
- Used `VS2015 x64 Native Tools Command Prompt` (it comes with MSVS2015) to build zlib:  
  `workspace\thirdparty\src>` `build-zlib.bat /yes`

- Downloaded OpenSSL source: https://github.com/openssl/openssl/releases
- Updated OpenSSL version in `workspace\scripts\tpversions.bat`
- In `VS2015 x64 Native Tools Command Prompt`:  
  `workspace\thirdparty\src>` `build-openssl.bat /yes`

- Downloaded libevent source (2.1.8-stable): https://github.com/nmathewson/Libevent/tree/release-2.1.8-stable
- Installed Strawberry Perl 5.26
- Updated libevent version in `workspace\scripts\tpversions.bat`
- In `VS2015 x64 Native Tools Command Prompt`:  
  `workspace\thirdparty\src>` `build-libevent.bat /yes`

- Download boost 1.62 for Win. _CAUTION_: unzip to a not too deep
  directory, otherwise it will have problems with long path names.
- Copy boost to `workspace/thirdparty/dist`

- Run `workspace>` `build-thrift-compiler.bat`
  - __This didn't work__ so I built manually:
  - `cd workspace\build`
  - `cmake.exe ..\thrift -DBISON_EXECUTABLE=..\thirdparty\dist\winflexbison\win_bison.exe -DCMAKE_BUILD_TYPE=Release -DFLEX_EXECUTABLE=..\thirdparty\dist\winflexbison\win_flex.exe -DWITH_MT=ON -DWITH_SHARED_LIB=OFF -G"Visual Studio 14 2015 Win64"`
  - `MSBUILD "Apache Thrift.sln" /p:Configuration=Release /p:Platform=x64 /t:thrift-compiler`
  - This got me two executables in `workspace/build/bin/...`: thrift.exe and thrift-bootstrap.exe


- Run `workspace>` `build-thrift.bat`
 - The above didn't work
 - clean `workspace\build`
 - `cd workspace\build`
 - `cmake.exe ..\thrift ^
    -G"Visual Studio 14 2015 Win64" ^
    -DBISON_EXECUTABLE=..\thirdparty\dist\winflexbison\win_bison.exe ^
    -DBOOST_ROOT=..\thirdparty\dist\boost_1_62_0 ^
    -DBOOST_LIBRARYDIR=..\thirdparty\dist\boost_1_62_0\lib64-msvc-14.0 ^
    -DCMAKE_INSTALL_PREFIX=..\dist\thrift-runtime ^
    -DCMAKE_BUILD_TYPE=Release ^
    -DFLEX_EXECUTABLE=..\thirdparty\dist\winflexbison\win_flex.exe ^
    -DLIBEVENT_ROOT=..\thirdparty\dist\libevent-2.1.8\vc140\x64\Release ^
    -DOPENSSL_ROOT_DIR=..\thirdparty\dist\openssl-1.1.0f\vc140\x64\release\dynamic ^
    -DOPENSSL_USE_STATIC_LIBS=OFF ^
    -DZLIB_LIBRARY=..\thirdparty\dist\zlib-1.2.11\vc140\x64\lib\zlib.lib ^
    -DZLIB_ROOT=..\thirdparty\dist\zlib-1.2.11\vc140\x64 ^
    -DWITH_BOOSTTHREADS=ON ^
    -DWITH_SHARED_LIB=OFF ^
    -DWITH_STATIC_LIB=ON`
  - `MSBUILD "Apache Thrift.sln" /p:Configuration=Release /p:Platform=x64 /t:INSTALL`
