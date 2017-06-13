# Finch introduction workshop

## Overview

The purpose of this workshop is to provide an introduction to [Finch] (and [circe], as a nice side-effect).

It will not make anyone an expert, but attendants should leave with enough knowledge to be able to learn the advanced topics by themselves
(or, if the need arises through a more advanced workshop later).



## Preparation

Before starting the workshop, attendants should have installed the following software:

* Java 8 ([instructions](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))
* latest version of SBT ([instructions](http://www.scala-sbt.org/download.html))

Additionally, the workshop's repository must be cloned locally, and the `start` tag checked out:

```bash
git clone git@github.com:nrinaudo/workshop-finch.git
git checkout start
```

It might also be useful to have the [slides](https://nrinaudo.github.io/workshop-finch/) open in a browser window.

Ideally, attendants would have pre-loaded the project in their IDE or editor of choice (we recommend Emacs + Ensime).



## Workshop flow

This workshop is organised in 3 main sections, at the end of which a few tasks will be asked of the attendants.

Some tasks are compulsory and really should be attempted before carrying on.

Some tasks are optional and, while interesting in themselves, are intended to keep fast attendants busy while the less experienced catch up.

Attendants that get stuck with a set of task and cannot get help from the host or other people can always stash their changes and checkout
one of the following tags:

- `1st_exercice`: all tasks in the first exercise completed
- `2nd_exercice`: all tasks in the second exercise completed
- `3rd_exercice`: all tasks in the third exercise completed

Please do not do so before attempting the corresponding tasks: the purpose is not to complete the exercises, but to attempt and struggle with them.
You will get much more out of the workshop if you don't cheat.


[circe]:http://circe.io
[Finch]:https://github.com/finagle/finch
