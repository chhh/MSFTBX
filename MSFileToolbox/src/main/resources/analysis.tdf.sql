BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `TimsCalibration` (
	`Id`	INTEGER,
	`ModelType`	INTEGER NOT NULL,
	`C0`	TEXT,
	`C1`	TEXT,
	`C2`	TEXT,
	`C3`	TEXT,
	`C4`	TEXT,
	`C5`	TEXT,
	`C6`	TEXT,
	`C7`	TEXT,
	`C8`	TEXT,
	`C9`	TEXT,
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `Segments` (
	`Id`	INTEGER,
	`FirstFrame`	INTEGER NOT NULL,
	`LastFrame`	INTEGER NOT NULL,
	`IsCalibrationSegment`	BOOLEAN NOT NULL,
	FOREIGN KEY(`LastFrame`) REFERENCES `Frames`(`Id`),
	FOREIGN KEY(`FirstFrame`) REFERENCES `Frames`(`Id`),
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `PropertyGroups` (
	`Id`	INTEGER,
	PRIMARY KEY(`Id`)
) WITHOUT ROWID;
CREATE TABLE IF NOT EXISTS `PropertyDefinitions` (
	`Id`	INTEGER,
	`PermanentName`	TEXT NOT NULL,
	`Type`	INTEGER NOT NULL,
	`DisplayGroupName`	TEXT NOT NULL,
	`DisplayName`	TEXT NOT NULL,
	`DisplayValueText`	TEXT NOT NULL,
	`DisplayFormat`	TEXT NOT NULL,
	`DisplayDimension`	TEXT NOT NULL,
	`Description`	TEXT NOT NULL,
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `Precursors` (
	`Id`	INTEGER,
	`LargestPeakMz`	REAL NOT NULL,
	`AverageMz`	REAL NOT NULL,
	`MonoisotopicMz`	REAL,
	`Charge`	INTEGER,
	`ScanNumber`	REAL NOT NULL,
	`Intensity`	REAL NOT NULL,
	`Parent`	INTEGER,
	FOREIGN KEY(`Parent`) REFERENCES `Frames`(`Id`),
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `PasefFrameMsMsInfo` (
	`Frame`	INTEGER NOT NULL,
	`ScanNumBegin`	INTEGER NOT NULL,
	`ScanNumEnd`	INTEGER NOT NULL,
	`IsolationMz`	REAL NOT NULL,
	`IsolationWidth`	REAL NOT NULL,
	`CollisionEnergy`	REAL NOT NULL,
	`Precursor`	INTEGER,
	FOREIGN KEY(`Frame`) REFERENCES `Frames`(`Id`),
	PRIMARY KEY(`Frame`,`ScanNumBegin`),
	FOREIGN KEY(`Precursor`) REFERENCES `Precursors`(`Id`)
) WITHOUT ROWID;
CREATE TABLE IF NOT EXISTS `MzCalibration` (
	`Id`	INTEGER,
	`ModelType`	INTEGER NOT NULL,
	`DigitizerTimebase`	REAL NOT NULL,
	`DigitizerDelay`	REAL NOT NULL,
	`T1`	REAL NOT NULL,
	`T2`	REAL NOT NULL,
	`dC1`	REAL NOT NULL,
	`dC2`	REAL NOT NULL,
	`C0`	TEXT,
	`C1`	TEXT,
	`C2`	TEXT,
	`C3`	TEXT,
	`C4`	TEXT,
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `GroupProperties` (
	`PropertyGroup`	INTEGER NOT NULL,
	`Property`	INTEGER NOT NULL,
	`Value`	TEXT NOT NULL,
	FOREIGN KEY(`PropertyGroup`) REFERENCES `PropertyGroups`(`Id`),
	PRIMARY KEY(`PropertyGroup`,`Property`),
	FOREIGN KEY(`Property`) REFERENCES `PropertyDefinitions`(`Id`)
) WITHOUT ROWID;
CREATE TABLE IF NOT EXISTS `GlobalMetadata` (
	`Key`	TEXT,
	`Value`	TEXT,
	PRIMARY KEY(`Key`)
);
CREATE TABLE IF NOT EXISTS `Frames` (
	`Id`	INTEGER,
	`Time`	REAL NOT NULL,
	`Polarity`	CHAR ( 1 ) NOT NULL CHECK(Polarity IN ( '+' , '-' )),
	`ScanMode`	INTEGER NOT NULL,
	`MsMsType`	INTEGER NOT NULL,
	`TimsId`	INTEGER,
	`MaxIntensity`	INTEGER NOT NULL,
	`SummedIntensities`	INTEGER NOT NULL,
	`NumScans`	INTEGER NOT NULL,
	`NumPeaks`	INTEGER NOT NULL,
	`MzCalibration`	INTEGER NOT NULL,
	`T1`	REAL NOT NULL,
	`T2`	REAL NOT NULL,
	`TimsCalibration`	INTEGER NOT NULL,
	`PropertyGroup`	INTEGER,
	`AccumulationTime`	REAL NOT NULL,
	`RampTime`	REAL NOT NULL,
	FOREIGN KEY(`PropertyGroup`) REFERENCES `PropertyGroups`(`Id`),
	FOREIGN KEY(`TimsCalibration`) REFERENCES `TimsCalibration`(`Id`),
	FOREIGN KEY(`MzCalibration`) REFERENCES `MzCalibration`(`Id`),
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `FrameProperties` (
	`Frame`	INTEGER NOT NULL,
	`Property`	INTEGER NOT NULL,
	`Value`	TEXT NOT NULL,
	FOREIGN KEY(`Property`) REFERENCES `PropertyDefinitions`(`Id`),
	PRIMARY KEY(`Frame`,`Property`),
	FOREIGN KEY(`Frame`) REFERENCES `Frames`(`Id`)
) WITHOUT ROWID;
CREATE TABLE IF NOT EXISTS `FrameMsMsInfo` (
	`Frame`	INTEGER,
	`Parent`	INTEGER,
	`TriggerMass`	REAL NOT NULL,
	`IsolationWidth`	REAL NOT NULL,
	`PrecursorCharge`	INTEGER,
	`CollisionEnergy`	REAL NOT NULL,
	FOREIGN KEY(`Frame`) REFERENCES `Frames`(`Id`),
	PRIMARY KEY(`Frame`)
);
CREATE TABLE IF NOT EXISTS `ErrorLog` (
	`Frame`	INTEGER NOT NULL,
	`Scan`	INTEGER,
	`Message`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `DiaFrameMsMsWindows` (
	`WindowGroup`	INTEGER NOT NULL,
	`ScanNumBegin`	INTEGER NOT NULL,
	`ScanNumEnd`	INTEGER NOT NULL,
	`IsolationMz`	REAL NOT NULL,
	`IsolationWidth`	REAL NOT NULL,
	`CollisionEnergy`	REAL NOT NULL,
	PRIMARY KEY(`WindowGroup`,`ScanNumBegin`),
	FOREIGN KEY(`WindowGroup`) REFERENCES `FrameMsMsWindowGroups`(`Id`)
) WITHOUT ROWID;
CREATE TABLE IF NOT EXISTS `DiaFrameMsMsWindowGroups` (
	`Id`	INTEGER,
	PRIMARY KEY(`Id`)
);
CREATE TABLE IF NOT EXISTS `DiaFrameMsMsInfo` (
	`Frame`	INTEGER,
	`WindowGroup`	INTEGER NOT NULL,
	FOREIGN KEY(`WindowGroup`) REFERENCES `FrameMsMsWindowGroups`(`Id`),
	FOREIGN KEY(`Frame`) REFERENCES `Frames`(`Id`),
	PRIMARY KEY(`Frame`)
);
CREATE TABLE IF NOT EXISTS `CalibrationInfo` (
	`KeyPolarity`	CHAR ( 1 ) CHECK(KeyPolarity IN ( '+' , '-' )),
	`KeyName`	TEXT,
	`Value`	TEXT,
	PRIMARY KEY(`KeyPolarity`,`KeyName`)
);
CREATE UNIQUE INDEX IF NOT EXISTS `PropertyDefinitionsIndex` ON `PropertyDefinitions` (
	`PermanentName`
);
CREATE INDEX IF NOT EXISTS `PrecursorsParentIndex` ON `Precursors` (
	`Parent`
);
CREATE INDEX IF NOT EXISTS `PasefFrameMsMsInfoPrecursorIndex` ON `PasefFrameMsMsInfo` (
	`Precursor`
);
CREATE UNIQUE INDEX IF NOT EXISTS `FramesTimeIndex` ON `Frames` (
	`Time`
);
CREATE VIEW Properties AS
    SELECT s.Id Frame, pd.Id Property, COALESCE(fp.Value, gp.Value) Value
    FROM Frames s
    JOIN PropertyDefinitions pd
    LEFT JOIN GroupProperties gp ON gp.PropertyGroup=s.PropertyGroup AND gp.Property=pd.Id
    LEFT JOIN FrameProperties fp ON fp.Frame=s.Id AND fp.Property=pd.Id;
COMMIT;
