package seedu.address.model.hirelah.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class ModelStorage {
    private static final Logger logger = LogsCenter.getLogger(ModelStorage.class);
    private final Path path;


    public ModelStorage(Path newPath) {
        this.path = newPath;
    }

    public Path getPath() {
        requireNonNull(path);
        return path;
    }

    /**
     * reads the data from the current Path to
     * retrieve all the information regarding Model.
     * @return OptionalModelList
     * @throws DataConversionException error when reading the file
     */
    public Optional<Boolean> readModel(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableModel> jsonModel = JsonUtil.readJsonFile(
                filePath, JsonSerializableModel.class);
        if (jsonModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(jsonModel.get().toModelType());
    }

    /**
     * Save the information of the Model
     * @param  source of the data. Cannot be null.
     */
    public void saveModel(boolean source) throws IOException {
        requireNonNull(source);
        requireNonNull(path);
        FileUtil.createIfMissing(path);
        JsonUtil.saveJsonFile(new JsonSerializableModel(source), path);
    }

}
