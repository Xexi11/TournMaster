package cat.udl.tidic.amb.tournmaster;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class UserDiffCallback extends DiffUtil.ItemCallback<User> {

    @Override
    public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem.equals(newItem);
    }
}
