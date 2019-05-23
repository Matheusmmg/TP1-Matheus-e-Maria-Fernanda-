// ----- ----- Class 1: Stdio ----- -----

package include;

import com.mtsystems.coot.Container;
import com.mtsystems.coot.NativeHelper;

public class Stdio {
	/**
	 * Standard streams.
	 *
	 * Standard input stream.
	 */
	public final static Container<Container<IoFile>> STDIN = new Container<Container<IoFile>>(1){};

	static {
		// The online demo uses the example library "libc.so.6".
		// Have a look at the translated programs for real values.
		NativeHelper h = NativeHelper.get("libc.so.6");
		h.bindVariable("stdin", STDIN);
		h.bindDirect(Stdio.class);
	}

	/**
	 * C89/C99 say they're macros.  Make them happy.
	 */
	public static Container<IoFile> stdin() {
		return STDIN.get();
	}

	/**
	 * Flush STREAM, or all streams if STREAM is NULL.
	 */
	public static native int fflush(Container<IoFile> stream);
}


// ----- ----- Class 2: IoMarker ----- -----

package include;

import com.mtsystems.coot.Container;
import com.mtsystems.coot.IntContainer;
import com.mtsystems.coot.NativeClass;

/**
 * A streammarker remembers a position in a buffer.
 */
public class IoMarker extends NativeClass {
	private final Container<Container<IoMarker>> next = new Container<Container<IoMarker>>(this, 1){};

	/**
	 * If _pos >= 0
	 * it points to _buf->Gbase()+_pos. FIXME comment
	 */
	private final Container<Container<IoFile>> sbuf = new Container<Container<IoFile>>(this, 1){};

	/**
	 * if _pos < 0, it points to _buf->eBptr()+_pos. FIXME comment
	 */
	private final IntContainer pos = new IntContainer(this, 1);

	public Container<IoMarker> getNext() {
		return next.get();
	}

	public Container<IoMarker> setNext(Container<IoMarker> newNext) {
		return next.set(newNext);
	}

	public Container<IoFile> getSbuf() {
		return sbuf.get();
	}

	public Container<IoFile> setSbuf(Container<IoFile> newSbuf) {
		return sbuf.set(newSbuf);
	}

	public int getPos() {
		return pos.get();
	}

	public int setPos(int newPos) {
		return pos.set(newPos);
	}
}


// ----- ----- Class 3: IoFile ----- -----

package include;

import static com.mtsystems.coot.NativeInformation.INT_SIZE;
import static com.mtsystems.coot.NativeInformation.LONG_SIZE;
import static com.mtsystems.coot.NativeInformation.POINTER_SIZE;

import com.mtsystems.coot.AbstractData;
import com.mtsystems.coot.Container;
import com.mtsystems.coot.IntContainer;
import com.mtsystems.coot.LongContainer;
import com.mtsystems.coot.NativeClass;
import com.mtsystems.coot.ShortContainer;
import com.mtsystems.coot.String8;

public class IoFile extends NativeClass {
	/**
	 * High-order word is _IO_MAGIC; rest is flags.
	 */
	private final IntContainer flags = new IntContainer(this, 1);

	/**
	 * Note:  Tk uses the _IO_read_ptr and _IO_read_end fields directly.
	 *
	 * Current read pointer
	 */
	private final Container<String8> ioReadPtr = new Container<String8>(this, 1){};

	/**
	 * End of get area.
	 */
	private final Container<String8> ioReadEnd = new Container<String8>(this, 1){};

	/**
	 * Start of putback+get area.
	 */
	private final Container<String8> ioReadBase = new Container<String8>(this, 1){};

	/**
	 * Start of put area.
	 */
	private final Container<String8> ioWriteBase = new Container<String8>(this, 1){};

	/**
	 * Current put pointer.
	 */
	private final Container<String8> ioWritePtr = new Container<String8>(this, 1){};

	/**
	 * End of put area.
	 */
	private final Container<String8> ioWriteEnd = new Container<String8>(this, 1){};

	/**
	 * Start of reserve area.
	 */
	private final Container<String8> ioBufBase = new Container<String8>(this, 1){};

	/**
	 * End of reserve area.
	 */
	private final Container<String8> ioBufEnd = new Container<String8>(this, 1){};

	/**
	 * The following fields are used to support backing up and undo.
	 *
	 * Pointer to start of non-current get area.
	 */
	private final Container<String8> ioSaveBase = new Container<String8>(this, 1){};

	/**
	 * Pointer to first valid character of backup area
	 */
	private final Container<String8> ioBackupBase = new Container<String8>(this, 1){};

	/**
	 * Pointer to end of non-current get area.
	 */
	private final Container<String8> ioSaveEnd = new Container<String8>(this, 1){};

	private final Container<Container<IoMarker>> markers = new Container<Container<IoMarker>>(this, 1){};

	private final Container<Container<IoFile>> chain = new Container<Container<IoFile>>(this, 1){};

	private final IntContainer fileno = new IntContainer(this, 1);

	private final IntContainer flags2 = new IntContainer(this, 1);

	/**
	 * This used to be _offset but it's too small.
	 */
	private final LongContainer oldOffset = new LongContainer(this, 1);

	/**
	 * 1+column number of pbase(); 0 is unknown.
	 */
	private final ShortContainer curColumn_U = new ShortContainer(this, 1);

	private final String8 vtableOffset = new String8(this, 1);

	private final String8 shortbuf = new String8(this, 1);

	/**
	 * char* _save_gptr;  char* _save_egptr;
	 */
	private final Container<AbstractData> lock = new Container<AbstractData>(this, 1){};

	private final LongContainer offset = new LongContainer(this, 1);

	private final Container<AbstractData> pad1 = new Container<AbstractData>(this, 1){};

	private final Container<AbstractData> pad2 = new Container<AbstractData>(this, 1){};

	private final Container<AbstractData> pad3 = new Container<AbstractData>(this, 1){};

	private final Container<AbstractData> pad4 = new Container<AbstractData>(this, 1){};

	private final LongContainer pad5_U = new LongContainer(this, 1);

	private final IntContainer mode = new IntContainer(this, 1);

	/**
	 * Make sure we don't get into trouble again.
	 */
	private final String8 unused2 = new String8(this, (int)(15 * ((long)INT_SIZE) - 4 * ((long)POINTER_SIZE) - ((long)LONG_SIZE)));

	public int getFlags() {
		return flags.get();
	}

	public int setFlags(int newFlags) {
		return flags.set(newFlags);
	}

	public String8 getIoReadPtr() {
		return ioReadPtr.get();
	}

	public String8 setIoReadPtr(String8 newIoReadPtr) {
		return ioReadPtr.set(newIoReadPtr);
	}

	public String8 getIoReadEnd() {
		return ioReadEnd.get();
	}

	public String8 setIoReadEnd(String8 newIoReadEnd) {
		return ioReadEnd.set(newIoReadEnd);
	}

	public String8 getIoReadBase() {
		return ioReadBase.get();
	}

	public String8 setIoReadBase(String8 newIoReadBase) {
		return ioReadBase.set(newIoReadBase);
	}

	public String8 getIoWriteBase() {
		return ioWriteBase.get();
	}

	public String8 setIoWriteBase(String8 newIoWriteBase) {
		return ioWriteBase.set(newIoWriteBase);
	}

	public String8 getIoWritePtr() {
		return ioWritePtr.get();
	}

	public String8 setIoWritePtr(String8 newIoWritePtr) {
		return ioWritePtr.set(newIoWritePtr);
	}

	public String8 getIoWriteEnd() {
		return ioWriteEnd.get();
	}

	public String8 setIoWriteEnd(String8 newIoWriteEnd) {
		return ioWriteEnd.set(newIoWriteEnd);
	}

	public String8 getIoBufBase() {
		return ioBufBase.get();
	}

	public String8 setIoBufBase(String8 newIoBufBase) {
		return ioBufBase.set(newIoBufBase);
	}

	public String8 getIoBufEnd() {
		return ioBufEnd.get();
	}

	public String8 setIoBufEnd(String8 newIoBufEnd) {
		return ioBufEnd.set(newIoBufEnd);
	}

	public String8 getIoSaveBase() {
		return ioSaveBase.get();
	}

	public String8 setIoSaveBase(String8 newIoSaveBase) {
		return ioSaveBase.set(newIoSaveBase);
	}

	public String8 getIoBackupBase() {
		return ioBackupBase.get();
	}

	public String8 setIoBackupBase(String8 newIoBackupBase) {
		return ioBackupBase.set(newIoBackupBase);
	}

	public String8 getIoSaveEnd() {
		return ioSaveEnd.get();
	}

	public String8 setIoSaveEnd(String8 newIoSaveEnd) {
		return ioSaveEnd.set(newIoSaveEnd);
	}

	public Container<IoMarker> getMarkers() {
		return markers.get();
	}

	public Container<IoMarker> setMarkers(Container<IoMarker> newMarkers) {
		return markers.set(newMarkers);
	}

	public Container<IoFile> getChain() {
		return chain.get();
	}

	public Container<IoFile> setChain(Container<IoFile> newChain) {
		return chain.set(newChain);
	}

	public int getFileno() {
		return fileno.get();
	}

	public int setFileno(int newFileno) {
		return fileno.set(newFileno);
	}

	public int getFlags2() {
		return flags2.get();
	}

	public int setFlags2(int newFlags2) {
		return flags2.set(newFlags2);
	}

	public long getOldOffset() {
		return oldOffset.get();
	}

	public long setOldOffset(long newOldOffset) {
		return oldOffset.set(newOldOffset);
	}

	public short getCurColumn_U() {
		return curColumn_U.get();
	}

	public short setCurColumn_U(short newCurColumn_U) {
		return curColumn_U.set(newCurColumn_U);
	}

	public byte getVtableOffset() {
		return vtableOffset.get();
	}

	public byte setVtableOffset(byte newVtableOffset) {
		return vtableOffset.set(newVtableOffset);
	}

	public String8 getShortbuf() {
		return shortbuf;
	}

	public AbstractData getLock() {
		return lock.get();
	}

	public AbstractData setLock(AbstractData newLock) {
		return lock.set(newLock);
	}

	public long getOffset() {
		return offset.get();
	}

	public long setOffset(long newOffset) {
		return offset.set(newOffset);
	}

	public AbstractData getPad1() {
		return pad1.get();
	}

	public AbstractData setPad1(AbstractData newPad1) {
		return pad1.set(newPad1);
	}

	public AbstractData getPad2() {
		return pad2.get();
	}

	public AbstractData setPad2(AbstractData newPad2) {
		return pad2.set(newPad2);
	}

	public AbstractData getPad3() {
		return pad3.get();
	}

	public AbstractData setPad3(AbstractData newPad3) {
		return pad3.set(newPad3);
	}

	public AbstractData getPad4() {
		return pad4.get();
	}

	public AbstractData setPad4(AbstractData newPad4) {
		return pad4.set(newPad4);
	}

	public long getPad5_U() {
		return pad5_U.get();
	}

	public long setPad5_U(long newPad5_U) {
		return pad5_U.set(newPad5_U);
	}

	public int getMode() {
		return mode.get();
	}

	public int setMode(int newMode) {
		return mode.set(newMode);
	}

	public String8 getUnused2() {
		return unused2;
	}
}


// ----- ----- Class 4: DemoTranslation ----- -----

package demo;

import static include.Stdio.fflush;
import static include.Stdio.stdin;

import java.io.IOException;
import java.util.Scanner;

public class DemoTranslation {
	public static void calcDesconto(float media, int matricula) {
		if(media >= 9.0) {
			System.out.println("Matrícula do aluno:\n" + matricula + "\n\nValor do desconto:\n30\%%\n");
		} else if(media >= 8.0) {
			System.out.println("Matrícula do aluno:\n" + matricula + "\n\nValor do desconto:\n10\%%\n");
		} else if(media >= 7.0) {
			System.out.println("Matrícula do aluno:\n" + matricula + "\n\nValor do desconto:\n5\%%\n");
		} else {
			System.out.println("Matrícula do aluno:\n" + matricula + "\n\nValor do desconto:\n0\%%\n");
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		int mat, cont;
		float mediaGeral;


		cont = 0; //Inicialização do contador
		while(cont < 4) {
			System.out.println("----------------------------------------------");
			System.out.println();
			System.out.println("Digite a matrícula:");
			mat = STDIN_SCANNER.nextInt();
			System.out.println();
			System.out.println("Digite a média geral:");
			mediaGeral = STDIN_SCANNER.nextFloat();
			System.out.println();
			System.out.print("----------------------------------------------");
			System.out.println();
			calcDesconto(mediaGeral, mat);
			cont++;
			fflush(stdin());
		}

		new ProcessBuilder("PAUSE").inheritIO().start().waitFor();
	}

	public final static Scanner STDIN_SCANNER = new Scanner(System.in);
}
